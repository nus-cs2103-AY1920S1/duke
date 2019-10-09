package duke.ui;

import duke.Duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Dog.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Snowball.png"));

    /**
     * Initialises the scroll pane's vvalue property  and adds a welcome message
     * to the dialogue container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(SpeechMaker.MESSAGE_WELCOME, dukeImage)
        );
    }

    /**
     * Sets the given Duke as the Duke instance for the current MainWindow.
     *
     * @param duke The instance of Duke to be used.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's response, then adds them to the dialog container. Clears user
     * input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();

        if (SpeechMaker.isByeMessage(dukeText)) {
            Platform.exit();
        }
    }
}
