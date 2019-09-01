package duke.view;

import duke.Duke;
import duke.Ui;

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
    private Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    /**
     * Initializes the main window (and prints welcome messages).
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.getWelcomeMessage(), dukeImage),
                DialogBox.getDukeDialog(Ui.getDatetimeFormatMessage(), dukeImage)
        );
    }

    /**
     * Sets main window's Duke object to the given Duke object.
     * @param duke The Duke object to be set to.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        this.ui = duke.getUi();
    }

    /**
     * Parses the user's input, executes it, and prints a response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        if (duke.getIsExiting()) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog("Exiting...", dukeImage)
            );
        } else {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }

        userInput.clear();
    }
}
