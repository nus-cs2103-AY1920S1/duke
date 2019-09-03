import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets duke to a Duke object.
     *
     * @param d Duke object to be set.
     */
    public void setDuke(Duke d) {
        duke = d;
        displayGreetings();
    }

    /**
     * Displays duke dialog box with greetings messages.
     */
    private void displayGreetings() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! I'm Duke\n" +
                        "     What can I do for you?", dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. Closes the window if exit command is inputted.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if(input.equals("bye")){
            userInput.clear();
            new Thread(() -> {
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException exc){
                    System.out.println("interrupted");
                }
                Platform.exit();}).start();
            } else {
            userInput.clear();
        }
    }
}