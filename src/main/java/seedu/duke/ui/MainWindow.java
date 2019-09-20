package seedu.duke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seedu.duke.core.Duke;

/**
 * Controller for seedu.duke.ui.MainWindow. Provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/117.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/commando.jpg"));

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        String introduction = "__________________________________________________\n"
                + "Sample Commands: \n"
                + "'todo Laundry' - Adds a To-do called Laundry\n"
                + "'deadline CS2105 Assignment /by 27/12/2019 2345' - Adds a deadline task\n"
                + "'event Dad's birthday /at Yishun' - Adds an event task\n"
                + "'stats all' - Views all stats\n"
                + "__________________________________________________\n";
        String welcomeString = "Hello! I'm Duke\n" + "What can I do for you?\n";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeString + introduction,
                dukeImage));
    }

    /**
     * Initialize duke.
     *
     * @param d Duke object.
     */
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

        Label inputText = new Label(input);
        inputText.setWrapText(true);

        Label responseText = new Label(response);
        responseText.setWrapText(true);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}