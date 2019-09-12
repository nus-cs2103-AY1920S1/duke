import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * Why do I have to give a shit about JavaFX T.T
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Tom.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Jerry.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    void setDuke(Duke d) {
        assert duke != null;
        duke = d;
        duke.initialize(this);
    }

    /**
     * prints out dialogue box in the MainWindow
     * @param s output String
     */
    void dukeSays(String s) {
        assert(s instanceof String);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(s, dukeImage)
        );
    }

    void closeSequence() {
        Window stage = scrollPane.getScene().getWindow();
        stage.hide();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();
        duke.getResponse(input);
    }
}