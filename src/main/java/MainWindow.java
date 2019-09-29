import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/panda.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/butterfly.jpg"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        displayMessage("Hi I am duke, What can I do for you?", false);
    }

    /**
     * Sets the Duke object.
     *
     * @param d Duke Object.
     */
    public void setDuke(Duke d) {
        duke = d;
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
                    .add(DialogBox.getUserDialog(message, userImage));
        } else {
            dialogContainer
                    .getChildren()
                    .add(DialogBox.getDukeDialog(message, dukeImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (duke.readyToExit()) {
            Platform.exit();
        } else {
            String input = userInput.getText();
            String response = duke.getResponse(input);
            displayMessage(input, true);
            displayMessage(response, false);
            userInput.clear();
        }
    }
}