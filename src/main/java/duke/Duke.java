package duke;

import duke.command.Command;

import duke.exception.DukeException;
import duke.exception.DukeIOException;

import duke.module.Parser;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javafx.stage.Stage;

/**
 * <h1>Duke</h1>
 * Stores user-defined tasks.
 *
 * @author  Kyungho Min
 * @version v0.1
 * @since   2019-09-01
 */
public class Duke extends Application {

    /** Handles user inputs and outputs. */
    private Ui ui;
    /** Stores the tasks inputted by the user. */
    private TaskList taskList;
    /** Saves the tasks in the TaskList to use at the next boot up. */
    private Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Initializes the necessary modules to run the Duke application.
     *
     * @throws DukeIOException When an error occurs during the input-output process or
     * during the parsing of the save file
     */
    public Duke() throws DukeIOException {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.load());
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        // Greet the user
        this.ui.greet();

        // Handle user input
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = this.ui.readCommand();
                String description = this.ui.readDescription();
                Command c = Parser.parseToCommand(command, description);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.printToUser(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        // Set properties of scrollPane
        scrollPane.setPrefSize(398, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // Set size of dialogContainer based on scrollPane size
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Make a TextField to write commands
        userInput = new TextField();
        userInput.setPrefSize(325.0, 40.0);
        userInput.setFont(Font.font("Arial", 14));

        // Make send Button
        sendButton = new Button("Send");
        sendButton.setPrefSize(70.0, 40.0);

        // Make an AnchorPane add previously defined nodes
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);

        scene = new Scene(mainLayout);

        // Anchor each nodes of AnchorPane
        AnchorPane.setLeftAnchor(scrollPane, 1.0);
        AnchorPane.setRightAnchor(scrollPane, 1.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, userInput.getPrefHeight() + 2);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setRightAnchor(userInput, sendButton.getPrefWidth() + 5);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Set stage properties
        stage.setTitle("Duke");
        stage.setMinHeight(300.0);
        stage.setMinWidth(400.0);
        stage.setScene(scene);

        // Show stage
        stage.show();
    }
    
}