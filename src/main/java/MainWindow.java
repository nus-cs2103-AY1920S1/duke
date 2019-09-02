import duke.DialogBox;
import duke.Duke;

import duke.MainUi;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialise MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        MainUi tmpUi = new MainUi();
        tmpUi.showWelcome();
        dialogContainer.getChildren()
            .add(DialogBox.getDukeDialog(new Label(tmpUi.getResponse()), new ImageView(dukeImage)));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container.
     * Clears the user input after processing.
     * Input containing bye as first word will terminate the program.
     */
    @FXML
    private void handleUserInput() {
        String uiInput = userInput.getText().trim();
        int index = uiInput.indexOf(' ');
        if (index < 0) {
            index = uiInput.length();
        }
        if (uiInput.substring(0, index).equals("bye")) {
            System.exit(0);
        }

        Label userText = new Label(uiInput);
        Label dukeText = new Label(duke.getResponse(uiInput));

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(userImage)),
            DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
        userInput.clear();
    }
}