package duke;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import duke.command.Command;
import duke.component.Parser;
import duke.component.TaskList;
import duke.component.Storage;
import duke.exception.DukeException;
import duke.ui.DialogBox;

/**
 * A class containing the main logic and UI elements of the Duke application.
 */
public class Duke extends Application {
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";

    private static final String DUKE_LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DUKE_INTRODUCTION = "Hello! I'm Duke\nWhat can I do for you?";
    private Image dukePic = new Image(this.getClass().getResourceAsStream("/images/DaMyth.png"));
    private Image userPic = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    private Storage fileMgr;
    private TaskList tasks;

    // Application UI elements
    private AnchorPane mainLayout;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Stage stage;
    private Scene scene;

    /**
     * Constructs a <code>Duke</code> application instance, an interactive task manager, with the default filepath.
     */
    public Duke() {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Constructs a <code>Duke</code> application instance, an interactive task manager, with a given file path.
     * 
     * @param filePath <code>String</code> containing the relative file path of the file to persist application
     *                 data to.
     */
    public Duke(String filePath) {
        try {
            this.fileMgr = new Storage(filePath);
        } catch (IOException e) {
            System.exit(1);
        }

        assert this.fileMgr instanceof Storage :
            "Attempting to proceed without succesfully creating a Storage!";

        // Attempt to re-construct TaskList from data in file
        try {
            tasks = fileMgr.readTaskList();
        } catch (DukeException e) {
            tasks = new TaskList();
        }

        assert this.tasks instanceof TaskList :
            "Attempting to proceed without successfully creating a TaskList!";
    }

    // ======================== DUKE APPLICATON WITH GRAPHICAL USER INTERFACE ========================

    // Schedules an action to be executed by a thread after a specified delay
    private static void deferAction(long delayDuration, Runnable action) {
        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                    action.run();
                }
            },
            delayDuration
        );
    }

    // Parses and executes a command input by a user through the GUI, returning the String output of the command
    private String handleCommand(String command) {
        // Parse the command to return a Command object
        try {
            Command c = Parser.parse(command);
            String commandOutput = c.execute(this.tasks, this.fileMgr);

            // If this command terminates Duke, schedule the application window to close in 0.5s
            if (c.willTerminate()) {
                Duke.deferAction(500, () -> {
                    Platform.exit();
                    System.exit(0);
                });
            }

            return commandOutput;
        } catch (DukeException e) {
            // Return the full error message
            return e.toString();
        }
    }

    // Instantiates and arranges the application window
    private void instantiateWindow() {
        // Scrollable container for displaying dialog between user and Duke
        this.dialogContainer = new VBox();
        this.scrollPane = new ScrollPane(this.dialogContainer);

        // For users to enter and send commands
        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        // Construct Node that allows child Nodes to be anchored to edges of the application window
        // Then set this Node as the root Node of the Scene
        this.mainLayout = new AnchorPane(scrollPane, userInput, sendButton);
        this.scene = new Scene(mainLayout);
    }

    // Applies styling to each element in the application window
    private void applyWindowStyling() {
        this.stage.setTitle("Duke");
        this.stage.setResizable(false);
        this.stage.setWidth(600.0);
        this.stage.setHeight(700.0);
        this.mainLayout.setPrefSize(600.0, 700.0);
        
        scrollPane.setPrefSize(585, 635);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVvalue(0.5);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        userInput.setPrefWidth(480.0);
        sendButton.setPrefWidth(100.0);
        
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    // Attaches event handlers to interactive elements
    private void attachEventHandlers() {
        this.dialogContainer.heightProperty().addListener((observable) -> this.scrollPane.setVvalue(1.0));

        this.sendButton.setOnMouseClicked((event) -> {
            this.handleUserInput();
        });
        
        this.userInput.setOnAction((event) -> {
            this.handleUserInput();
        });
    }

    /**
     * Creates two <code>Label</code> elements (one for the user command and the other for Duke's response), then
     * attaches both to the dialog container.
     */
    private void handleUserInput() {
        String command = this.userInput.getText();
        this.userInput.clear();

        // GUARD: against empty commands
        if (command.equals("")) {
            return;
        }

        Label userText = new Label(command);
        Label dukeText = new Label(this.handleCommand(command));

        this.dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(this.userPic)),
            DialogBox.getDukeDialog(dukeText, new ImageView(this.dukePic))
        );
    }

    // Invoked once on application startup - shows Duke's logo then welcome message
    private void showIntroduction() {
        Label dukeLogo = new Label(Duke.DUKE_LOGO);
        dukeLogo.setFont(new Font("Consolas", 12));
        dukeLogo.setMaxWidth(this.scrollPane.getWidth());
        dukeLogo.setAlignment(Pos.CENTER);
        this.dialogContainer.getChildren().add(dukeLogo);

        Label dukeWelcome = new Label(Duke.DUKE_INTRODUCTION);
        this.dialogContainer.getChildren().add(
            DialogBox.getDukeDialog(dukeWelcome, new ImageView(this.dukePic))
        );
    }
    
    /**
     * Instantiates the Duke application window.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        this.instantiateWindow();
        this.applyWindowStyling();
        this.attachEventHandlers();
    
        this.stage.setScene(this.scene);
        this.stage.show();

        this.showIntroduction();
    }
}
