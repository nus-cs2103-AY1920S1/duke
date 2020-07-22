package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.strip().equals("")) {
            return;
        }
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();
        duke.handleUserCommand(input);
    }

    @FXML
    private void handleKeyTyped() {
        String input = userInput.getText();
        if (input.strip().equals("")) {
            sendButton.setDisable(true);
        } else {
            sendButton.setDisable(false);
        }
    }

    protected void handleDukeResponse(String response) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }
}