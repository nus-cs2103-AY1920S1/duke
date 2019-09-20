package ui;

import duke.Storage;
import exceptions.DukeException;
import javafx.animation.PauseTransition;
import javafx.beans.binding.BooleanBinding;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;

import duke.Duke;
import duke.Ui;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.util.Duration;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    /* The default EventHandler for text field when enter key is pressed */
    private EventHandler<ActionEvent> textFieldEvent;

    private Duke duke;
    private Ui ui;
    private Storage storage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/eminem.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/lelouch2.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Initialize the EventHandler used by the text field
        textFieldEvent = userInput.getOnAction();
        // Set the action of textField to do nothing by default
        userInput.setOnAction(null);

        // Disable the button and label actions if text field is empty

        // Disable the button if text field is empty
        Supplier<BooleanBinding> supplier = () -> new BooleanBinding() {
            {
                super.bind(userInput.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return userInput.getText().trim().isEmpty();
            }
        };
        sendButton.disableProperty().bind(supplier.get());

        // Set the action of textField to do nothing if text field is empty
        userInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                userInput.setOnAction(null);
            } else {
                userInput.setOnAction(MainWindow.this.textFieldEvent);
            }
        });

    }

    /**
     * Initializes the duke property and the ui object to be used by MainWindow class.
     * Also outputs the welcome message for Duke onto the screen.
     *
     * @param duke Duke object associated with the Window.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        this.ui = duke.getUi();
        this.storage = duke.getStorage();
        DialogBox LoadBox;
        if (storage.getHasTxtFile() && storage.getHasDataFolder()) {
            LoadBox = DialogBox.getDukeDialog("The list of tasks has been successfully loaded from" +
                    " the text file in the data folder!", dukeImage);
        } else if (storage.getHasDataFolder()) {
            LoadBox = DialogBox.getDukeDialog("A text file was not found in the data folder. A text" +
                    " file representing an empty task list has been created!", dukeImage);
        } else {
            LoadBox = DialogBox.getDukeDialog("A data folder was not found. A new data folder" +
                    " has been created along with a text file representing an empty task list!", dukeImage);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.getDukeAsciiArt(), dukeImage),
                LoadBox,
                DialogBox.getDukeDialog("Hello, I'm Duke! What can I do for you?", dukeImage)
        );
    }

    /**
     * Remove all dialog-boxes from the window to prevent cluttering.
     */
    public void clearWindow() {
        dialogContainer.getChildren().clear();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        // Output a different coloured background Label as Duke's response
        // should an error occur.
        try {
            String response = duke.getResponse(input);
            if (input.equals("clear")) {
                clearWindow();
                dialogContainer.getChildren().add(
                        DialogBox.getDukeDialog(response, dukeImage)
                );
            } else {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(response, dukeImage)
                );
            }
        } catch (IOException | DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeErrorDialog(e.getMessage(), dukeImage)
            );
        }
        userInput.clear();
        if (duke.getShouldExitProgram()) {
            // Pause the program to show bye message before closing the program
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }
    }
}