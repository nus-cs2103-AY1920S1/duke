package myduke.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import myduke.Duke;
import myduke.type.MessageFormatType;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    //Class Variables
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Duke dukeEngine;
    private final Image userImage;
    private final Image dukeImage;

    /**
     * Constructor for Main window.
     */
    public MainWindow() {
        userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
        dukeEngine = new Duke(
            message -> {
                dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
            },
            MessageFormatType.MESSAGE_FORMAT_NO_BOUNDARY_WITHOUT_INDENT);
    }

    /**
     * Initialises the window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dukeEngine.respondToQuery("reinitialise");
    }

    /**
     * Creates two dialog boxes, one to echo the user's query and the other containing Duke's response.
     * If the command is an exit command, exit the program.
     * Clears the text box after processing query.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.isEmpty() || sendButton.isDisabled()) {
            return;
        }

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        findResponse(input);
        userInput.clear();
    }

    /**
     * Finds and display the responses to a user query.
     *
     * @param input query from the user.
     */
    private void findResponse(String input) {
        boolean ignoreQuery = input.isEmpty() || sendButton.isDisabled();
        if (ignoreQuery || dukeEngine.respondToQuery(input)) {
            return;
        }

        sendButton.setDisable(true);
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            } finally {
                Platform.runLater(() -> Platform.exit());
            }
        });
        thread.start();
    }

    /**
     * Shuts down Duke.
     */
    public void shutdown() {
        findResponse("bye");
    }
}