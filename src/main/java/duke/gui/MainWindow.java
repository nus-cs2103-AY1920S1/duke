package duke.gui;

import duke.ui.Duke;

import javafx.fxml.FXML;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    public static String MAIN_WINDOW_RESOURCE_PATH = "/view/MainWindow.fxml";
    public static String USER_IMAGE_RESOURCE_PATH = "/images/user_icon.png";
    public static String DUKE_IMAGE_RESOURCE_PATH = "/images/duke_icon.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Label dukeStorageName;
    @FXML
    SimpleBooleanProperty dukeActivityStatus;

    private Duke duke;

    private Image userImage;
    private Image dukeImage;

    @FXML
    private void initialize() {
        dukeActivityStatus = new SimpleBooleanProperty();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userImage = new Image(Gui.class.getResourceAsStream(USER_IMAGE_RESOURCE_PATH));
        dukeImage = new Image(Gui.class.getResourceAsStream(DUKE_IMAGE_RESOURCE_PATH));
        dialogContainer.setPrefWidth(scrollPane.getPrefViewportWidth());
    }


    /**
     * Sets the current working Duke instance which the GUI will interface with.
     *
     * @param duke The Duke instance to interface with
     */
    public void setDuke(Duke duke) {
        assert duke != null;
        this.duke = duke;
        dukeStorageName.textProperty().bind(duke.observableStorageName);
    }

    /**
     * Tries to load an TaskList from the save file with the specified name, and displays the Response from the attempt.
     *
     * @param fileName The file from which to try to load a TaskList from
     */
    void loadExistingTaskList(String fileName) {
        assert fileName != null;

        Response response = duke.getResponse("load " + fileName);

        if (!response.wasCausedByError()) {
            display(DialogBox.getDukeNormalDialog(response.toString(), dukeImage));
        } else {
            display(DialogBox.getDukeErrorDialog(response.toString(), dukeImage));
        }
    }

    /**
     * Displays the Response from activating Duke.
     */
    void activateDuke() {
        Response response = duke.greet();

        assert response != null;
        dukeActivityStatus.set(response.isActive());

        assert response.toString() != null;
        display(DialogBox.getDukeNormalDialog(response.toString(), dukeImage));
    }


    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null;

        Response res = duke.getResponse(input);

        if (res.wasCausedByError()) {
            displayError(input, res.toString());
        } else {
            displayDialog(input, res.toString());
        }

        userInput.clear();
        dukeActivityStatus.set(res.isActive());
    }

    /**
     * Displays the user's message, and Duke's response message.
     *
     * @param input The user's message to Duke
     * @param response Duke's response to the user
     */
    private void displayDialog(String input, String response) {
        assert input != null;
        assert response != null;

        display(
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
        assert input != null;
        assert response != null;

        display(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeErrorDialog(response, dukeImage)
        );
    }

    private void display(DialogBox... dialogs) {
        for (DialogBox dialog : dialogs) {
            dialogContainer.getChildren().add(dialog);
        }
    }
}