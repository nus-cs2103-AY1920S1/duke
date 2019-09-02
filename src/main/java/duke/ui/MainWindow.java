package duke.ui;

import duke.Duke;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/profile.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(new Ui().getGreeting(), dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void setDukeMessage(String input) {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(input, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Add user message to the dialog
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage)
        );

        // Get response and add it to the dialog
        String response = duke.getResponse(input);
        if (response != null) {
            dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }
}
