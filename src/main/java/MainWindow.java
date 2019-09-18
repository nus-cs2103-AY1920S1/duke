// @@author LeowWB-reused
// Original code by Jeffrey Lum, and can be found in the tutorials directory of this repository.
// Reused with minor modifications.

import java.io.FileInputStream;
import java.io.IOException;
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

    private Image userImage;
    private Image dukeImage;

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    }

    /**
     * Sets the Duke implementation that controls this window.
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(duke.printGreeting(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.processInputLine(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
