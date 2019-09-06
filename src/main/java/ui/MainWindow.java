package ui;

import core.Duke;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

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
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();

        boolean isExit = duke.consumeUserInput(input);
        if (isExit) {
            exit();
        }
    }

    /**
     * Run exit sequence.
     */
    private void exit() {
        sendButton.setDisable(true);
        userInput.setDisable(true);
        runExitTimer();
    }

    /**
     * Make Duke count down to exit.
     */
    private void runExitTimer() {
        AtomicInteger i = new AtomicInteger(3);
        Timeline timeline = new Timeline(new KeyFrame(
            Duration.seconds(1),
            ae -> {
                if (i.get() <= 0) {
                    Platform.exit();
                } else {
                    addDukeResponse("Shutting down in..." + i);
                    i.getAndDecrement();
                }
            }));
        timeline.setCycleCount(4);
        timeline.play();
    }

    /**
     * Make Duke respond in chat with given String.
     *
     * @param response String which Duke will respond with.
     */
    public void addDukeResponse(String response) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
    }
}