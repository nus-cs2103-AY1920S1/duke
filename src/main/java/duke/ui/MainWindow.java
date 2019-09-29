package duke.ui;

import duke.Duke;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    /** Main scroll pane of dialogs of the window. */
    @FXML
    private ScrollPane scrollPane;
    /** VBox container for dialog boxes in the scroll pane. */
    @FXML
    private VBox dialogContainer;
    /** User input text field for receiving inputs. */
    @FXML
    private TextField userInput;
    /** Send button for sending input with mouse inputs. */
    @FXML
    private Button sendButton;

    /** Duke instance provided by DukeApplication's call to setDuke. */
    private Duke duke;

    /** JavaFX image representing the user to use for dialog boxes. */
    private Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/DaUser.png"));
    /** JavaFX image representing duke to use for dialog boxes. */
    private Image dukeImage = new Image(
            this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /** Exit handler for closing the main window. */
    public EventHandler<ActionEvent> exitHandler;

    /**
     * JavaFX FXML method called after initialising this controller.
     * Binds the scrollPane's vertical scroll position to the height
     * of the dialog container.
     */
    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the instance of duke associated with this window.
     *
     * @param dukeInstance Instance of duke.
     */
    void setDuke(Duke dukeInstance) {
        duke = dukeInstance;
    }

    /**
     * Creates one response dialog box displaying the input message.
     *
     * @param msg String message to display.
     */
    public void showMessage(String msg) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(msg, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage));
        duke.processInput(input);
        userInput.clear();
    }
}