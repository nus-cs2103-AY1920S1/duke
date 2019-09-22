package duke.gui;

import duke.Duke;
import duke.exceptions.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.NoSuchElementException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        this.duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = this.userInput.getText();
            String response = this.duke.getResponse(input);
            assert response.length() != 0 : "Duke has no response (invalid behaviour)";
            this.dialogContainer.getChildren().addAll(
                    new UserDialogBox(input, this.userImage),
                    new DukeDialogBox(response, this.dukeImage)
            );
            this.userInput.clear();
        } catch (DukeException e) {
            handleError("Sorry, I didn't understand your command! " + e.getMessage());
        } catch (NoSuchElementException e) {
            handleError("Sorry, please enter a command!");
        }
    }

    /**
     * Handles any error encountered by getting the GUI to print a suitable error
     * messages for the user.
     * @param errorMsg An error message that Duke prints to the GUI for the user.
     */
    private void handleError(String errorMsg) {
        assert errorMsg.length() != 0 : "Duke error message cannot be blank";
        this.dialogContainer.getChildren().addAll(
                new DukeDialogBox(errorMsg, this.dukeImage));
        this.userInput.clear();
    }
}
