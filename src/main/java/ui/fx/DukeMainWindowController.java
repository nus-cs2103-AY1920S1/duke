package ui.fx;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyEvent;

import ui.input.InputHandler;

/**
 * Main window for JavaFx Ui.
 */
public class DukeMainWindowController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Image userImage;
    private Image dukeImage;

    private InputHandler inputHandler;

    public DukeMainWindowController() {
        userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
        dukeImage = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets up input handler to listen for user inputs.
     * @param input handler
     */
    public void configureController(InputHandler input) {
        this.inputHandler = input;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (!input.equals("")) {
            printUserMessage(input);

            inputHandler.updateAllListeners(input);

            userInput.clear();
        }
    }

    /**
     * Sends user input to listeners on Enter key press.
     * @param keyEvent user key press
     */
    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            handleUserInput();
        }
    }

    /**
     * Prints message from program.
     * @param message message to be printed
     */
    public void printDukeMessage(String message) {
        dialogContainer.getChildren().addAll(FxDialogBox.getDukeDialog(message, dukeImage));
    }

    /**
     * Prints message from user.
     * @param message message to be printed
     */
    public void printUserMessage(String message) {
        dialogContainer.getChildren().addAll(
                FxDialogBox.getUserDialog(message, userImage)
        );
    }
}

