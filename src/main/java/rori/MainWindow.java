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

    private Rori rori;
    private RoriManager roriManager;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image roriImage = new Image(this.getClass().getResourceAsStream("/images/Rori.png"));

    private boolean isInTutorial;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setRori(Rori d) {
        rori = d;
        roriManager = this.rori.getRoriManager();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rori's reply 
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = null;
        if (isInTutorial) {
            response = roriManager.getTutorialResponse(input);
            isInTutorial = false;
        } else {
            response = rori.getResponse(input);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRoriDialog(response, roriImage)
        );
        userInput.clear();
    }

    /**
     * Prints the welcome message for the user.
     */
    public void welcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getRoriDialog(this.roriManager.welcomeMessage(), roriImage)
        );
    }

    /**
     * Prints to ask if the user wants a tutorial on their initial startup of the program.
     */
    public void tutorialMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getRoriDialog(this.roriManager.getTutorial(), roriImage)
        );
        isInTutorial = true;
    }
}