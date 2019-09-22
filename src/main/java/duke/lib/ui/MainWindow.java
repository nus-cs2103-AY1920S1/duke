package duke.lib.ui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventTarget;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private Stage stage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private UI ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/NewUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/NewDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void init(Duke d) {
        this.duke = d;
        this.ui = new UI();
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

        if (duke.isExit()) {
            Platform.exit();
        }
    }

    public void outputError(Exception e) {
        output(e.getMessage());
    }

    private void output(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
    }

    public void showWelcome() {
        output(ui.getWelcomeMessage());
    }
}