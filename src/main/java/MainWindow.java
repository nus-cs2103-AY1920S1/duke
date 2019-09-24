import execution.UI;
import javafx.application.Platform;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserV3.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Dukev2.jpg"));

    /**
     * Constructs the main window.
     */
    public MainWindow() {
    }

    /**
     * Initialisation of GUI.
     */
    @FXML
    public void initialize() {
        UI ui = new UI();

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String style = "-fx-background-color: rgba(40, 42, 47, 1);";
        this.dialogContainer.setStyle(style);

        //duke welcome message upon opening GUI
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.welcomeMsg(), dukeImage)
        );
    }

    /**
     * Set Controller for MainWindow.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        UI ui = new UI();

        //exits the application after the user says bye
        if (response.equals(ui.goodbyeMsg())) {
            Platform.exit();
            System.exit(0);
        }
    }
}