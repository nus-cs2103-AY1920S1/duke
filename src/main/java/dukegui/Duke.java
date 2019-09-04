package dukegui;

import duke.exception.DukeException;
import duke.exception.DukeIOException;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;

import dukegui.command.Command;
import dukegui.component.DialogueBox;
import dukegui.module.Parser;

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

import javafx.scene.text.Font;

import javafx.stage.Stage;

/**
 * <h1>Duke GUI</h1>
 * Stores user-defined tasks.
 *
 * @author  Kyungho Min
 * @version v0.1
 * @since   2019-09-01
 */
public class Duke extends Application {

    private static final String DUKE_GUI_HELLO = "Hello! I'm Duke!\nWhat can I do for you?";

    /** Stores the tasks inputted by the user. */
    private TaskList taskList;
    /** Saves the tasks in the TaskList to use at the next boot up. */
    private Storage storage;
    /** Indicates whether this program should keep running or quit. */
    private boolean isExit;

    public Duke() throws DukeIOException {
        this.storage = new Storage();
        this.taskList = new TaskList(storage.load());
        this.isExit = false;
    }

    private ScrollPane scrollPane;
    private VBox dialogueContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/ryan.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/D.png"));

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogueContainer = new VBox();
        scrollPane.setContent(dialogueContainer);

        // Set properties of scrollPane
        scrollPane.setPrefSize(398, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);

        // Set size of dialogueContainer based on scrollPane size
        dialogueContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Make a TextField to write commands
        userInput = new TextField();
        userInput.setPrefSize(325.0, 40.0);
        userInput.setFont(Font.font("Arial", 14));

        // Make send Button
        sendButton = new Button("Send");
        sendButton.setPrefSize(70.0, 40.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogueContainer's height changes.
        dialogueContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

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

        this.greetUser();
    }

    private void greetUser() {
        Label dukeText = new Label(AutoResponse.DUKE_HELLO.replace("    ", ""));
        dialogueContainer.getChildren().addAll(
                new DialogueBox(dukeText, new ImageView(duke))
        );
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogueContainer.getChildren().addAll(
                new DialogueBox(userText, new ImageView(user)),
                new DialogueBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
        if (this.isExit) {
            this.exit();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        String[] command = input.split( " ", 2);
        try {
            Command c = Parser.parseToCommand(command[0], command[1]);
            this.isExit = c.isExit();
            return c.getResponse(this.taskList, this.storage);
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                Command c = Parser.parseToCommand(command[0], "");
                this.isExit = c.isExit();
                return c.getResponse(this.taskList, this.storage);
            } catch (DukeException e2) {
                return e2.getMessage();
            }
        } catch (DukeException e1) {
            return e1.getMessage();
        }
    }

    private void exit() {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Platform.exit();
        });
        t.start();
    }
    
}