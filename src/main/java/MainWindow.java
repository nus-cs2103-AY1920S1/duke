import javafx.event.ActionEvent;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("images/Bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() throws Exception {
        String input = userInput.getText();
        if (input.isEmpty()) {
            System.exit(0);
        }
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (input.equals("bye")) {
            duke.terminate();
        }
        userInput.clear();
    }

    @FXML
    public void quitTheApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Used to print out the message when the app is started.
     */
    public void showWelcomeMessage() {
        String input = "Hi! Welcome to Radomir!\n\n";
        input += "Type 'help' to get the list of commands available.\n\n"
                + "For first time users, type 'tutorial' to know more about the app."
                + " (Warning, this will delete your current list!)";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(input, dukeImage)
        );
    }
}