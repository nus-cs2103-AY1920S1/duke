import javafx.fxml.FXML;
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

    private Trackr trackr;

    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/patrick.png"));
    private Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/spongebob.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTrackr(Trackr t) {
        trackr = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = trackr.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImg),
                DialogBox.getDukeDialog(response, dukeImg)
        );
        userInput.clear();
        if (input.equals("bye")) {
            exitProgram();
        }
    }

    /**
     * Exits program after a delay of 2 seconds.
     */
    private void exitProgram() {
        Timer tm = new Timer();
        tm.schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 2000);
    }

    /**
     * Displays welcome message to user on app start.
     */
    public void showWelcome() {
        String welcomeMsg = welcomeMsg();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMsg, dukeImg)
        );
    }

    /**
     * Returns welcome messsage to be displayed on app start.
     * @return String Welcome message
     */
    private String welcomeMsg() {
        return "Hello! I'm Spongebob!\nWhat can I do for you?\n";
    }
}