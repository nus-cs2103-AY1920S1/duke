package duke.ui;

import static duke.MyPaths.MANAGER_PROFILE;
import static duke.MyPaths.USER_PROFILE;

import duke.Duke;
import duke.reminder.Reminder;
import duke.ui.DialogBox;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_PROFILE));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(MANAGER_PROFILE));

    private static final String MSG_WELCOME = "Hello! I'm Nezuko ><\n" + "What can I do for you?\n";

    private static final int NUMBER_OF_REMINDER = 5;

    /**
     * Initializes a MainWindow object.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(MSG_WELCOME, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Displays the reminder in a dialogue box.
     */
    public void displayReminder() {
        assert duke != null;
        Reminder reminder = new Reminder(duke.getSheet(), duke.getUi());
        reminder.remind(NUMBER_OF_REMINDER);
    }

    public String getInput() {
        return this.userInput.getText();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        duke.start();
        userInput.clear();
    }

    void displayMsg(String response) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, dukeImage));
    }
}