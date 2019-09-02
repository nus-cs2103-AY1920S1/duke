package duke;

import command.Command;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main Duke class.
 */
public class Duke extends Application {
    private String line;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/panda.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/butterfly.jpg"));
    private boolean readyToExit = false;

    @Override
    public void start(Stage stage) {
        String filePath = "list.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException duke) {
            ui.showLoadingError(duke);
            tasks = new TaskList();
        }

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

        // add Style to the GUI program.
        addStyle(stage, mainLayout);

        // greets the user
        displayMessage("Hi I am duke, What can I do for you?", false);

        // add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        // scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            if (readyToExit) {
                Platform.exit();
            } else {
                handleUserInput();
            }
        });

        userInput.setOnAction((event) -> {
            if (readyToExit) {
                Platform.exit();
            } else {
                handleUserInput();
            }
        });
    }

    /**
     * Function that styles the GUI.
     *
     * @param stage The Stage GUI.
     * @param mainLayout The AnchorPane of the GUI.
     */
    private void addStyle(Stage stage, AnchorPane mainLayout) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.centerOnScreen();

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
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        displayMessage(userText, true);
        displayMessage(dukeText, false);
        userInput.clear();
    }

    /**
     * Generates a message to the user.
     *
     * @param message Message to be sent from the bot to user.
     * @param isUserInput Identifies if the message is an user input.
     */
    private void displayMessage(String message, boolean isUserInput) {
        if (isUserInput) {
            dialogContainer
                    .getChildren()
                    .add(DialogBox.getUserDialog(new Label(message), new ImageView(user)));
        } else {
            dialogContainer
                    .getChildren()
                    .add(DialogBox.getDukeDialog(new Label(message), new ImageView(duke)));
        }
    }

    /**
     * Returns a String response by the bot based on the user input.
     *
     * @param input Input by the user.
     * @return Output response of String type.
     */
    private String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.getResponse(tasks, ui, storage);
            if (c.isExit()) {
                this.setExit();
            }
            return response;
        } catch (DukeException e) {
            return ui.showLoadingError(e);
        }
    }

    /**
     * Sets the state of the program to be ready to exit
     * by the next user command.
     */
    private void setExit() {
        this.readyToExit = true;
    }

}
