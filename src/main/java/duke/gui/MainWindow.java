package duke.gui;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.DukeException;
import duke.exception.DukeStorageException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MainWindow extends AnchorPane implements Ui {
    private final Storage storage = new Storage("duke.txt");
    private final TaskList tasks = new TaskList();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    /**
     * Initialises the MainWindow.
     */
    @FXML
    public void initialize() {
        try {
            storage.loadTasks(tasks);
        } catch (DukeStorageException e) {
            showMessage(e.getMessage());
        }

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }
        addUserDialog(input);
        userInput.clear();

        ArrayList<String> responses = new ArrayList<>();
        ArrayList<String> warnings = new ArrayList<>();
        ArrayList<String> errors = new ArrayList<>();
        try {
            Command command = Parser.parse(input);
            CommandResult result = command.execute(tasks, storage);
            if (result.isExit()) {
                Platform.exit();
            }
            responses.addAll(result.getMessages());
            warnings.addAll(result.getWarnings());
        } catch (DukeException e) {
            errors.add((e.getMessage()));
        }

        for (String response : responses) {
            showMessage(response);
        }
        for (String warning : warnings) {
            showWarning(warning);
        }
        for (String error : errors) {
            showError(error);
        }
    }

    private void addDialogBox(DialogBox dialogBox) {
        dialogContainer.getChildren().add(dialogBox);
    }

    private void addUserDialog(String dialog) {
        addDialogBox(DialogBox.getUserDialog(dialog));
    }

    @Override
    public void showMessage(String message) {
        addDialogBox(DialogBox.getDukeDialog(message));
    }

    @Override
    public void showWarning(String warning) {
        addDialogBox(DialogBox.getDukeWarning(warning));
    }

    @Override
    public void showError(String error) {
        addDialogBox(DialogBox.getDukeError(error));
    }

    @Override
    public void showWelcome() {
        addDialogBox(DialogBox.getDukeDialog("Hello from Duke! What can I do for you?"));
    }
}
