import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

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

    private TaskChick taskChick;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image chickyImage = new Image(this.getClass().getResourceAsStream("/images/chicky.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTaskChick(TaskChick tc) {
        taskChick = tc;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    void handleUserInput() {
        String input = userInput.getText();
        String response = taskChick.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTaskChickDialog(response, chickyImage)
        );
        userInput.clear();
        // ends the program when user inputs "bye"
        if (input.equals("bye")) {
            Timer tm = new Timer();
            tm.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 2000);
        }
    }

    /**
     * Displays welcome message to user when they first open Task Chick.
     */
    public void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getTaskChickDialog("Hello, I'm Task Chick!\n\nWhat can I do for you " +
                        "today?\n\nEnter "
                                + "'help' to see how you can use me ^_^", chickyImage)
        );
    }
}