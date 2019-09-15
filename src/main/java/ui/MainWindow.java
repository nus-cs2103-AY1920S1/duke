package ui;

import javafx.animation.PauseTransition;
import javafx.beans.binding.BooleanBinding;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;

import duke.Duke;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.util.Duration;
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
     * Initializes the duke property to be used by MainWindow class.
     * Also outputs the welcome message for Duke onto the screen.
     *
     * @param duke
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello, I'm Duke! What can I do for you?", dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.replaceAll("\\s+", "").equals("")) {

        }
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (duke.getShouldExitProgram()) {
            // Pause the program to show bye message before closing the program
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }
    }
}