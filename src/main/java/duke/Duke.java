package duke;

import command.Command;
import task.TaskList;
import ui.UserInterface;
import ui.DialogBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Runs the program skeleton: Event flow.
 */
public class Duke /*extends Application*/ {

    private Storage storage;
    private TaskList tasks;
    private UserInterface ui;

    /*
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));
    */

    public Duke(String filepath) {
        startup(filepath);
    }

    /*
    @Override

    public void start(Stage stage) {
        startup("data/dukeData.txt");

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Step 3. Add functionality to handle user input.

        //welcome message
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.welcome(), duke));

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
    */

    /*
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    /*
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    */

    /*
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    /*
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText().trim(), user),
                DialogBox.getDukeDialog(userInput.getText().trim(), duke)
        );
        userInput.clear();
    }
    */


    /**
     * Parses user inputs and return appropriate replies.
     * @param input User's action.
     * @return Reply from duke.
     */
    public String getResponse(String input) {
        try {
            boolean isExit = false;

            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            storage.save(tasks.getAllTasks());

            if(isExit) {
                return ui.goodbye();
            } else {
                return c.toString();
            }

        } catch (DukeException | ParseException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Constructs Duke object.
     * @param filePath Specified file destination.
     */
    public void startup(String filePath) {
        ui = new UserInterface();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | ParseException e) {
            ui.showError(e.getMessage());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public UserInterface getUi() {
        return ui;
    }
}