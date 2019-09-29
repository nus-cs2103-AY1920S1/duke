import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow Controller, which provides the layout for the other controls.
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
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userPic.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dukePic.png"));

    /**
     * Method to initialize several properties upon launch. Creates the opening
     * message for Duke as well.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.showWelcome(), dukeImage));
    }

    /**
     * Method to initialise Duke in MainWindow.
     *
     * @param d Duke object
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Takes in the user's input and creates two dialog boxes. One contains the user input
     * and the other contains Duke's reply to the input. Input is cleared after
     * processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equals("exit")) {
            sendButton.setDisable(true);
            userInput.setDisable(true);
        }
    }
}
