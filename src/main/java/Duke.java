import duke.command.Command;

import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;


/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various tasks.
 */
public class Duke extends Application {
    /** User Interface of Duke that handles input and output to and from the command line. */
    private Ui ui;
    /** Storage where the Tasks are retrieved from and stored to. */
    private Storage storage;
    /** List of Tasks Duke is currently tracking. */
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Duke that instantiates the Ui and Storage classes.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
    }

    public static void main(String[] args) {
        new Duke().runCli();
    }

    @Override
    public void start(Stage stage) {
        loadTasksFromStorage();

        // Step 1. Setting up required components

        // The container for the content of the chat to scroll.
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

        //Step 2. Formatting the window to look as expected
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Show greeting message at start
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ui.getGreetingMsg()), new ImageView(duke))
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
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.executeGui(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.getErrorMsg(e);
        }
    }

    /**
     * Runs and starts the Duke chatbot program.
     */
    public void runCli() {
        loadTasksFromStorage();

        ui.printGreetingMsg();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);
                isExit = c.isExit();
                c.executeCli(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printErrorMsg(e);
            }
        }

        ui.printExitMsg();
    }

    private void loadTasksFromStorage() {
        try {
            tasks = storage.getTasks();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.printErrorMsg(e);
            tasks = new TaskList();
        }
    }
}
