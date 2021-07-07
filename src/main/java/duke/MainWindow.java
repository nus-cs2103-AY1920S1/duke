package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for duke.MainWindow. Provides the layout for the other controls.
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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input)
        );
        userInput.clear();
        duke.handleUserCommand(input);

        if (input.equals("bye")) {
            Platform.exit();
        }
    }

    /**
     * Disables the sendButton if the user input field is empty.
     */
    @FXML
    private void preventEmptyInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            sendButton.setDisable(true);
        } else {
            sendButton.setDisable(false);
        }
    }

    /**
     * Adds the duke dialog into the dialogBox in the GUI.
     * @param response duke response to be added.
     */
    protected void handleDukeResponse(String response) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(response)
        );
    }
}