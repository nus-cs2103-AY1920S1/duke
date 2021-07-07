package ui;

import core.Duke;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for UI.MainWindow. Provides the layout for the other controls.
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
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showGreetingMessage(dialogContainer, dukeImage);
    }

    /**
     * Sets the Core.Duke variable of this object to the Duke object
     * provided as parameter.
     * @param d The duke object to be referenced by this object's 
     *     duke object.
     */
    public void setVariables(Duke d, Stage s) {
        duke = d;
        stage = s;
    }

    /**
     * Creates the greeting message from Core.Duke in the
     * appropriate dialogbox form.
     */
    @FXML
    private static void showGreetingMessage(VBox dialogContainer, Image image) {
        String dukeGoodbyeText = "Hello! I'm Duke" + "\n" + "What can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeGoodbyeText, image)
        );
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
        if (input.toLowerCase().equals("bye")) {
            stageCloser(stage);
        }
    }

    private void stageCloser(Stage stage) {
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(event -> stage.close());
        delay.play();
    }

}