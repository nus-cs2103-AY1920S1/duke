package duke.main;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Wookie.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Yoda.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        Image image = new Image("/images/Planet.png");
        BackgroundSize size = new BackgroundSize(600.0, 600.0, true, true, true, true);
        BackgroundImage bi= new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
        dialogContainer.setBackground(new Background(bi));
        DialogBox dukeWelcome = DialogBox.getDukeDialog(duke.getWelcome(), dukeImage);

        assert dukeWelcome != null : "dukeWelcome should not be null";

        dialogContainer.getChildren().addAll(dukeWelcome);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);

        assert userDialog != null : "userDialog should not be null";
        assert dukeDialog != null : "dukeDialog should not be null";

        dialogContainer.getChildren().addAll(userDialog, dukeDialog);

        userInput.clear();
        if (input.equals("bye")) {
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            System.exit(0);
                        }
                    },
                    1000
            );
        }
    }
}
