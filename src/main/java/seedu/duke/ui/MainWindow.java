package seedu.duke.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.model.Duke;

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

    //Icon made by Smashicons (https://www.flaticon.com/authors/smashicons) from www.flaticon.com
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    //Icon made by Freepik (https://www.flaticon.com/authors/freepik) from www.flaticon.com
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));
    private Image developerImage = new Image(this.getClass().getResourceAsStream("/images/Developer.png"));
    private Image exceptionImage = new Image(this.getClass().getResourceAsStream("/images/Oops.png"));
    private Image logoImage = new Image(this.getClass().getResourceAsStream("/images/LogoV3.png"));

    /**
     * Initialises the program.
     * Binds height of the dialog container to the scrollPane.
     * Displays to the user the welcome message of Duke.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                LogoDialog.getDukeLogo(logoImage, dukeImage),
                DialogBox.getDukeDialog(Ui.showWelcome(), dukeImage));
    }

    /**
     * Sets the Duke instance to be the version of Duke program to be run.
     * @param d A version of Duke to be run from this instance of MainWindow.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing seedu.duke.model.Duke's reply.
     * Appends the two dialog boxes to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException, DukeException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        assert userImage != null : "User profile image is null.";
        assert dukeImage != null : "Duke profile image is null.";

        Image outputImage = dukeImage;

        if (Ui.aboutMessage().equals(response)) {
            assert dukeImage != null : "Developer profile image is null.";
            outputImage = developerImage;
        } else if (response.startsWith("OOPS!")) {
            assert dukeImage != null : "Exception duke image is null.";
            outputImage = exceptionImage;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, outputImage)
        );
        userInput.clear();
        if (duke.getIsExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(0.9));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}