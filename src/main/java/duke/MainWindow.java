package duke;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Pooh.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Piglet.png"));

    public MainWindow() {
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Assigns the duke variable and creates a dialog box for Duke's welcome message.
     * @param d The duke instance.
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(
            new Group(DialogBox.getDukeDialog(duke.getWelcome(), dukeImage)));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
            new Group(DialogBox.getUserDialog(input, userImage)),
            new Group(DialogBox.getDukeDialog(response, dukeImage))
        );
        userInput.clear();
    }
}