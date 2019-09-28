package duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.application.Platform.exit;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    // To track cursor position
    static double xPos;
    static double yPos;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greetUser();
    }

    public void setDuke(Duke d) {
        duke = d;
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
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            exitDuke();
        }
    }

    @FXML
    private void greetUser() {
        // Greet user upon initialization
        dialogContainer.getChildren().add(DialogBox
                .getDukeDialog("Hello I'm Duke\nWhat can I do for you?", dukeImage));
    }

    @FXML
    private void exitDuke() {
        String temp = duke.getResponse("bye"); //Explicit "bye message", Hacky.
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xPos);
        stage.setY(event.getScreenY() - yPos);
    }

    @FXML
    void pressed(MouseEvent event) {
        xPos = event.getSceneX();
        yPos = event.getSceneY();
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage stage = ((Stage) ((Node)event.getSource()).getScene().getWindow());
        stage.setIconified(true);
    }

    @FXML
    void closeWindow(MouseEvent event) {
        exitDuke();
    }
}
