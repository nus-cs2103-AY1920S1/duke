import duke.util.Duke;
import duke.util.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    @FXML
    private FXMLLoader loader;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Set initial attributes for controllers in the GUI.
     */
    @FXML
    public void initialize() {
        setScrollPaneHeight();
        addWelcomeMessage();
        customiseTextInputBar();
    }

    private void setScrollPaneHeight() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void addWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.welcomeMessage(), dukeImage)
        );
    }

    private void customiseTextInputBar() {
        userInput.setStyle("-fx-text-inner-color: white; -fx-background-color:  #355951;");
    }

    /**
     * Sets duke for MainWindow.
     *
     * @param d Duke object
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sets loader for MainWindow.
     *
     * @param l Loader object
     */
    public void setLoader(FXMLLoader l) {
        loader = l;
    }

    /**
     * Exits the application.
     */
    private void exit() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        String byeMessage = "Bye. Hope to see you again soon!";
        if (response.equals(byeMessage)) {
            exit();
        }
    }

}