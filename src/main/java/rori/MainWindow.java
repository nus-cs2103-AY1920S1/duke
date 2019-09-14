import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image nomralRoriImage = new Image(this.getClass().getResourceAsStream("/images/NormalRori.png"));
    private final Image angryRoriImage = new Image(this.getClass().getResourceAsStream("/images/AngryRori.png"));

    private Image roriImage = new Image(this.getClass().getResourceAsStream("/images/NormalRori.png"));

    private boolean isInTutorial;
    private Color roriShadowColor = Color.SKYBLUE;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setRori(Rori rori) {
        this.rori = rori;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rori's reply 
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = null;
        boolean hasError = false;
        if (isInTutorial) {
            response = rori.getTutorialResponse(input);
            isInTutorial = false;
            roriImage = nomralRoriImage;
        } else {
            try {
                response = rori.getResponse(input);
                roriImage = nomralRoriImage;
            } catch (RoriException error) {
                response = error.getMessage();
                roriImage = angryRoriImage;
                hasError = true;
            }
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRoriDialog(response, roriImage, hasError)
        );
        userInput.clear();
    }

    /**
     * Prints the welcome message for the user.
     */
    public void welcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getRoriDialog(this.rori.welcomeMessage(), roriImage, false)
        );
    }

    /**
     * Prints to ask if the user wants a tutorial on their initial startup of the program.
     */
    public void tutorialMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getRoriDialog(this.rori.getTutorial(), roriImage, false)
        );
        isInTutorial = true;
    }
}