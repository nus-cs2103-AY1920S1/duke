package duke.ui;

import duke.Duke;
import duke.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Custom container for the application.
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

    private Duke duke = new Duke();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/jx.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/keanu.png"));

    /**
     * Set up when MainWindow is initialized.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String hiMessage = duke.initialize();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(hiMessage, dukeImage));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String fullCommand = userInput.getText();
        String response = duke.getResponse(fullCommand);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(fullCommand, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (duke.applicationShouldExit()) {
            Main.exit();
        }
    }
}
