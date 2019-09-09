package duke.gui;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.DukeException;
import duke.exception.DukeStorageException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Storage storage = new Storage("duke.txt");
    private TaskList tasks = new TaskList();
    private Image userAvatar = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeAvatar = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Initialises the MainWindow.
     */
    @FXML
    public void initialize() {
        try {
            tasks = storage.loadTasks();
        } catch (DukeStorageException e) {
            addDukeDialog(e.getMessage());
        }

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }
        addUserDialog(input);
        userInput.clear();

        String response = null;
        String warning = null;
        String error = null;
        try {
            Command command = Parser.parse(input);
            CommandResult result = command.execute(tasks, storage);
            if (result.isExit()) {
                Platform.exit();
            }
            response = result.hasMessages() ? String.join("\n", result.getMessages()).stripTrailing() : null;
            warning = result.hasWarnings() ? String.join("\n", result.getWarnings()).stripTrailing() : null;
        } catch (DukeException e) {
            error = e.getMessage();
        }

        if (response != null) {
            addDukeDialog(response);
        }
        if (warning != null) {
            addDukeDialog(warning);
        }
        if (error != null) {
            addDukeDialog(error);
        }
    }

    private void addUserDialog(String dialog) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(dialog, userAvatar));
    }

    private void addDukeDialog(String dialog) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dialog, dukeAvatar));
    }
}
