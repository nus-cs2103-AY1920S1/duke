package duke.gui;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeStorageException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Outermost component of the Duke GUI.
 */
public class MainWindow extends AnchorPane implements Ui {
    private final Storage storage = new Storage("duke.txt");
    private final TaskList tasks = new TaskList();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

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

        try {
            Command command = Parser.parse(input);
            command.execute(tasks, this, storage);
            if (command.shouldExit()) {
                Platform.exit();
            }
        } catch (DukeException e) {
            showError((e.getMessage()));
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

    @Override
    public void showBye() {
    }
}
