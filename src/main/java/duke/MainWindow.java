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
    private DukeManager dukeManager;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    private boolean isInTutorial;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dukeManager = this.duke.getDukeManager();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply 
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = null;
        if (isInTutorial) {
            response = dukeManager.getTutorialResponse(input);
            isInTutorial = false;
        } else {
            response = duke.getResponse(input);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Prints the welcome message for the user.
     */
    public void welcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(this.dukeManager.welcomeMessage(), dukeImage)
        );
    }

    /**
     * Prints to ask if the user wants a tutorial on their initial startup of the program.
     */
    public void tutorialMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(this.dukeManager.getTutorial(), dukeImage)
        );
        isInTutorial = true;
    }
}