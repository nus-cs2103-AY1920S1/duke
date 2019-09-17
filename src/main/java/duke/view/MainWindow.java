package duke.view;

import duke.Duke;
import duke.exception.DukeShutDownException;
import duke.util.Ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

/**
 * JavaFX Controller for MainWindow. Provides the layout for the MainWindow,
 * and defines the behaviour for on-screen GUI elements via fxml.
 */
public class MainWindow extends AnchorPane {
    private static final int SHUTDOWN_DELAY = 1750;

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
     * Initializes the main window elements and displays the greeting message from Duke.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        //scrollPane.setFitToHeight(false);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.HELLO, dukeImage));
    }

    public void setDuke(Duke d) {
        this.duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        this.publishUserInput(input);
        try {
            String response = duke.getResponse(input);
            this.publishDukeResponse(response);
        } catch (DukeShutDownException e) {
            this.publishDukeResponse(Ui.GOODBYE);
            this.closeWindowAndExit();
        }
    }

    // closes duke gracefully. should only be called when a shutdown signal.
    // is received from the ByeCommand
    private void closeWindowAndExit() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, SHUTDOWN_DELAY);
    }

    // publishes Duke response to the chat window.
    private void publishDukeResponse(String response) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
    }

    // publishes user response to the chat window.
    private void publishUserInput(String input) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();
    }
}