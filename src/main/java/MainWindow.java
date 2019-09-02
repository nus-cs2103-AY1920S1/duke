import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jermi.component.Jermi;

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

    private Jermi jermi;

    private boolean[] shouldExit = {false};
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image jermiImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    public void setJermi(Jermi jermi) {
        this.jermi = jermi;
        this.dialogContainer.getChildren().addAll(
                DialogBox.getJermiDialog(this.jermi.initialiseStorage("data/jermi.txt"), this.jermiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.jermi.getResponse(input, this.shouldExit);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getJermiDialog(response, this.jermiImage)
        );
        userInput.clear();
        if (this.shouldExit[0]) {
            Platform.exit();
            System.exit(0);
        }
    }
}