package duke.gui;

import duke.Duke;
import duke.core.Ui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;

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

    public SimpleBooleanProperty isExit;
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        isExit = new SimpleBooleanProperty();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void showWelcome(){
        String welcomeMessage = new Ui().showWelcomeGui();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(
                        welcomeMessage,
                        dukeImage));
    }

    public void load() {
        String message = duke.loadTasks();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(
                        message,
                        dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            isExit.set(true);
        }
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
