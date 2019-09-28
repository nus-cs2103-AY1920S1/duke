package duke.frontend;

import duke.exception.CompleteTaskException;
import duke.exception.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.TimeUnit;

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

    /**
     * Sets the current Duke instance that the GUI will interacts with.
     *
     * @param d the duke instance to be set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Displays Duke welcome message.
     */
    @FXML
    void showDukeIntro() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.displayIntro(), dukeImage)
        );
        userInput.clear();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. Exits the program if user requests so.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            response = duke.getResponse(input);
        } catch (DukeException e) {
            response = e.getMessage();
        } catch (NumberFormatException e) {
            response = "Sorry! The format of your command is wrong.";
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.toLowerCase().startsWith("bye")) {
            System.exit(0);
        }
    }
}
