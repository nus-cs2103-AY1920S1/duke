package bari;

import java.util.Timer;
import java.util.TimerTask;
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

    private Bari bari;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bariImage = new Image(this.getClass().getResourceAsStream("/images/bari.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBari(Bari d) {
        bari = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Bari's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bari.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBariDialog(response, bariImage)
        );
        userInput.clear();
        // This response is only produced by an ExitCommand
        if (response.equals("Bye!")) {
            // Wait for 0.5 seconds and exit
            Timer tm = new Timer();
            tm.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 500);
        }
    }
}
