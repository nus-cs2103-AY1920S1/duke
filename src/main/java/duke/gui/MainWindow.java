package duke.gui;

import duke.ui.Duke;

import javafx.fxml.FXML;

import javafx.beans.property.SimpleBooleanProperty;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import duke.ui.Response;

/**
 * The main window of the JavaFX GUI for Duke.
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
    @FXML
    public SimpleBooleanProperty activityStatus;

    private Duke duke;

    private Image userImage = new Image(Gui.class.getResourceAsStream("/images/dog.jpg"));
    private Image dukeImage = new Image(Gui.class.getResourceAsStream("/images/fatCat.png"));

    @FXML
    public void initialize() {
        activityStatus = new SimpleBooleanProperty();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    /**
     * Sets the current working Duke instance which the GUI will interface with.
     *
     * @param duke The Duke instance to interface with
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        activityStatus.set(true);
    }

    /**
     * Tries to load an TaskList from the specified file path, and displays the Response from the attemp.
     *
     * @param filePath The file path from which to try to load a TaskList from
     */
    public void loadExistingTaskList(String filePath) {
        Response response = duke.setUp(filePath);
        if (!response.wasCausedByError()) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeNormalDialog(
                            response.toString(),
                            dukeImage));
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeErrorDialog(
                            response.toString(),
                            dukeImage));
        }
    }

    /**
     * Displays the Response from activating Duke.
     */
    public void activateDuke() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeNormalDialog(
                        duke.greet().toString(),
                        dukeImage));
    }


    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response res = duke.getResponse(input);
        if (res.wasCausedByError()) {
            displayError(input, res.toString());
        } else {
            displayDialog(input, res.toString());
        }
        userInput.clear();
        activityStatus.set(res.isActive());
    }

    /**
     * Displays the user's message, and Duke's response message.
     *
     * @param input The user's message to Duke
     * @param response Duke's response to the user
     */
    private void displayDialog(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeNormalDialog(response, dukeImage)
        );
    }

    /**
     * Displays the user's message and Duke's error response message.
     *
     * @param input The user's message to Duke which causes an error
     * @param response Duke's error response message
     */
    private void displayError(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeErrorDialog(response, dukeImage)
        );
    }
}