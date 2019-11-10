package duke.ui;

import duke.Duke;
import duke.Main;

import duke.command.CommandResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String FXML = "/view/MainWindow.fxml";
    private final FXMLLoader fxmlLoader = new FXMLLoader();

    /** ScrollPane of the main window. **/
    @FXML
    private ScrollPane scrollPane;
    /** Vertical Box to contain the conversation. **/
    @FXML
    private VBox dialogContainer;
    /** Textfield to receive user input. **/
    @FXML
    private TextField userInput;
    /** Button to send user input to the system. **/
    @FXML
    private Button sendButton;

    /** Instance of duke used to drive the system. **/
    private Duke duke;
    /** Primary stage of all views. **/
    private Stage primaryStage;

    /** Profile picture of the user. **/
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userCat.png"));

    /** Profile picture of Duke. **/
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dukeCat.png"));

    private final Logger logger = Logger.getLogger(MainWindow.class.getName());

    /**
     * Initialises the main window.
     * @param primaryStage Primary stage to contain descendent views.
     */
    public MainWindow(Stage primaryStage) {

        this.primaryStage = primaryStage;

        fxmlLoader.setLocation(getFxmlFileUrl(FXML));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(primaryStage);

        duke = new Duke();
        duke.run();

        try {
            fxmlLoader.load();
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(Message.WELCOME, dukeImage)
            );

        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Displays the primary stage.
     */
    public void show() {
        primaryStage.show();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResult response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getUserFeedback(), dukeImage)
        );
        userInput.clear();

        if (response.isExit()) {
            handleExit();
        }
    }

    /**
     * Handles actions to do upon exit.
     */
    private void handleExit() {
        logger.info("---------Bye! See you again----------");
        System.exit(1);
    }

    /**
     * Gets the URL of the FXML file used.
     * @param fxmlFileName name of FXML file.
     * @return URL of the FXML file.
     */
    private static URL getFxmlFileUrl(String fxmlFileName) {
        requireNonNull(fxmlFileName);
        URL fxmlFileUrl = Main.class.getResource(fxmlFileName);
        return requireNonNull(fxmlFileUrl);
    }

}