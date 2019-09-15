package duke;

import duke.ui.Ui;
import java.util.Date;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

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
    @FXML
    private ImageView headerProfilePicture;

    private Duke duke;

    private Image dukeImage = new Image(this.getClass()
            .getResourceAsStream("/images/doke.png"));

    public MainWindow() {
    }

    /**
     * Initialises the window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        headerProfilePicture.setImage(dukeImage);
        headerProfilePicture.setClip(new Circle(16, 12, 12));
    }

    /**
     * Shows a welcome message from Duke.
     */
    public void showWelcome() {
        dialogContainer.getChildren().addAll(
                DateBox.getDateBox(new Date()),
                DialogBox.getDukeDialog(Ui.getWelcomeMessage(), dukeImage)
        );
    }

    /**
     * Shows a generic error message from Duke.
     */
    public void showError() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.getLoadingError(), dukeImage)
        );
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container.
     * If the command is an exit command, exit the program.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.isEmpty()) {
            return;
        }

        String response = duke.getResponse(input);

        if (response.equals(Duke.EXIT_COMMAND)) {
            Platform.exit();
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}