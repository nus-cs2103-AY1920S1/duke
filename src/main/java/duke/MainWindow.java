package duke;

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
    
    private Duke duke;
    
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/icon2.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/icon1.png"));
    
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
    
        //@@author {Parcly-Taxel}-reused
        //{Exits the program after a set timing of input command 'bye'}
        if (input.equals("bye")) {
            Timer tm = new Timer();
            tm.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 2000);
        }
        //@@author
    }
    
    /**
     * Shows welcome message when user starts the application.
     */
    public void showWelcome() {
        String welcomeMsg = Ui.welcome();
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(welcomeMsg, dukeImage)
        );
    }
}