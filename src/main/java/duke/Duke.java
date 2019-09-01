package duke;

import duke.command.Command;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Duke is a personal assistant that helps manage tasks in a list.
 */
public class Duke extends Application {

    private static final String FILEPATH = "\\data\\duke.txt";

    private boolean isExiting = false;

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Starts a new Duke session, loading any existing tasks from previous sessions from the hard disk.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Processes user input and prints a response.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        ui.showUserInput(input, dialogContainer);

        if (isExiting) {
            ui.showDukeMessage("Exiting...", dialogContainer);
        } else {
            String response = getResponse(input);
            ui.showDukeMessage(response, dialogContainer);
        }

        userInput.clear();
    }

    /**
     * Takes a command by the user, parses and executes it, then returns a text response.
     * @param fullCommand The user's input as a String.
     * @return A String representing Duke's response.
     */
    private String getResponse(String fullCommand) {
        try {
            Command c = Parser.parseCommand(fullCommand);
            isExiting = c.isExit();
            if (isExiting) {
                exitDuke();
            }
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Starts the application by initializing the UI and scanning for input.
     * @param stage The stage for JavaFX to display the application's GUI.
     */
    @Override
    public void start(Stage stage) {

        // container for the content of the chat to scroll

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // formatting the window to look as expected

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // print welcome message and info about datetime format
        ui.showWelcomeMessage(dialogContainer);
        ui.showDateTimeFormatMessage(dialogContainer);

        // add functionality to handle user input

        sendButton.setOnMouseClicked(event -> handleUserInput());

        userInput.setOnAction(event -> handleUserInput());

        // scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
    }

    /**
     * Waits 3 seconds, then exits the app.
     */
    private void exitDuke() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 3000);
    }
}
