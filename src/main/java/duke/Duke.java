package duke;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.stage.Stage;

import duke.command.Command;
import duke.component.Parser;
import duke.component.TaskList;
import duke.component.Storage;
import duke.component.Window;
import duke.exception.DukeException;
import duke.ui.DialogBox;

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
    private Window window;
    private boolean isRunning;

    // Application UI elements
    private AnchorPane mainLayout;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Stage stage;
    private Scene scene;

    /**
     *  Constructs a <code>Duke</code> application instance, an interactive task manager, with the default filepath.
     */
    public Duke() {
        this(DEFAULT_FILEPATH);
    }

    /**
     *  Constructs a <code>Duke</code> application instance, an interactive task manager, with a given file path.
     *  @param filePath <code>String</code> containing the relative file path of the file to persist application
     *                  data to.
     */
    public Duke(String filePath) {
        this.isRunning = true;
        this.window = new Window();
        
        try {
            this.fileMgr = new Storage(filePath);
        } catch (IOException e) {
            this.window.print("Error creating file to write application data. Exiting!");
            System.exit(1);
        }

        assert this.fileMgr instanceof Storage :
            "Attempting to proceed without succesfully creating a Storage!";

        // Attempt to re-construct TaskList from data in file
        try {
            tasks = fileMgr.readTaskList();
        } catch (DukeException e) {
            this.window.print(String.format("%s\n\nInitialised with empty TaskList", e.toString()));
            tasks = new TaskList();
        }

        assert this.tasks instanceof TaskList :
            "Attempting to proceed without successfully creating a TaskList!";
    }

    /** 
     *  Initiates the Duke application instance, allowing users to input commands to interact with the task maanger.
     */
    public void run() {
        // Show Duke's logo and welcome message
        this.window.showWelcome(Duke.DUKE_LOGO, Duke.DUKE_INTRODUCTION);

        while (this.isRunning && this.window.hasCommand()) {
            String command = this.window.readCommand();
            
            // Parse the command to return a Command object
            try {
                Command c = Parser.parse(command);
                this.window.print(c.execute(tasks, fileMgr));
                this.isRunning = !c.willTerminate();
            } catch (DukeException e) {
                this.window.print(e.toString());
            }
        }
    }
    
    /**
     *  Driver main method.
     *  @param args an array of <code>String</code> arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    // ======================== DUKE APPLICATON WITH GRAPHICAL USER INTERFACE ========================

    // Parses and executes a command input by a user through the GUI, returning the String output of the command
    private String handleCommand(String command) {
        // Parse the command to return a Command object
        try {
            Command c = Parser.parse(command);
            this.isRunning = !c.willTerminate();
            return c.execute(this.tasks, this.fileMgr);
        } catch (DukeException e) {
            // Return the full error message
            return e.toString();
        }
    }

    // Instantiates and arranges the application window with message box, scroll pane, input area and send button
    private void instantiateWindow() {
        // Scrollable containerFor displaying dialog between user and Duke
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

    // Applies styling to each of the elements in the application window
    private void applyWindowStyling() {
        // Style image nodes

        // this.duke.setClip(new Circle(25.0, 25.0, 25.0));
        // this.user.setClip(new Circle(25.0, 25.0, 25.0));

        // Set title and application window size
        this.stage.setTitle("Duke");
        this.stage.setResizable(false);
        this.stage.setWidth(400.0);
        this.stage.setHeight(600.0);
        this.mainLayout.setPrefSize(400.0, 600.0);
        
        // Set settings of scrollpane and dialog container
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVvalue(0.5);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        
        // Anchor child nodes to application window
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
     *  Creates two <code>Label</code> elements (one for the user command and the other for Duke's response), then
     *  attaches both to the dialog container.
     */
    private void handleUserInput() {
        String command = this.userInput.getText();
        this.userInput.clear();

        Label userText = new Label(command);
        Label dukeText = new Label(this.handleCommand(command));

        this.dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(this.userPic)),
            DialogBox.getDukeDialog(dukeText, new ImageView(this.dukePic))
        );

        // If a command has set Duke to terminate, schedule the application window to close in 1s
        if (!this.isRunning) {
            new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Platform.exit();
                        System.exit(0);
                    }
                },
                500
            );
        }
    }

    // Invoked on applications startup - shows Duke's welcome message
    private void showIntroduction() {
        Label dukeWelcome = new Label(Duke.DUKE_INTRODUCTION);
        this.dialogContainer.getChildren().add(
            DialogBox.getDukeDialog(dukeWelcome, new ImageView(this.dukePic))
        );
    }
    
    /**
     *  Instantiates the Duke application window.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        // Instantiate application window elements
        this.instantiateWindow();

        // Style application window elements
        this.applyWindowStyling();

        // Attach event handlers for interactive elements
        this.attachEventHandlers();
    
        this.stage.setScene(this.scene);
        this.stage.show();

        this.showIntroduction();
    }
}
