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
    /** Scroll pane. */
    @FXML
    private ScrollPane scrollPane;
    /** Dialog container. */
    @FXML
    private VBox dialogContainer;
    /** User input. */
    @FXML
    private TextField userInput;
    /** Send button. */
    @FXML
    private Button sendButton;
    /** Jermi. */
    private Jermi jermi;
    /** Indicator for if the program should close. */
    private boolean[] shouldExit = {false};
    /** Display image of user. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    /** Display image of Jermi. */
    private Image jermiImage = new Image(this.getClass().getResourceAsStream("/images/Jermi.png"));

    /**
     * Allows the scroll pane to scroll down automatically when the dialog container stretches beyond
     * the confines of the scroll pane.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /**
     * Stores {@link Jermi} reference, initialises the storage and displays the welcome message.
     *
     * @param jermi Jermi reference.
     */
    public void setup(Jermi jermi) {
        this.jermi = jermi;
        this.dialogContainer.getChildren().addAll(
                DialogBox.getJermiDialog(this.jermi.initialiseStorage("data/jermi.txt"), this.jermiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jermi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Closes application if exit command is given.
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