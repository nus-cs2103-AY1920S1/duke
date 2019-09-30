package duke.gui;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeStorageException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Map;

/**
 * Outermost component of the Duke GUI.
 */
public class MainWindow extends AnchorPane implements Ui {
    private static final Map<Class<? extends Task>, String> TASK_SHORT_NAME_MAP = Map.of(
        Todo.class, "[T]",
        Event.class, "[E]",
        Deadline.class, "[D]"
    );
    private static final Map<Boolean, String> TASK_STATUS_MAP = Map.of(
        true, "[✓]",
        false, "[X]"
    );
    private final Storage storage = new Storage("duke.txt");
    private final TaskList tasks = new TaskList();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    @Override
    public String getTaskRepresentation(final Task task) {
        return TASK_SHORT_NAME_MAP.get(task.getClass())
            + TASK_STATUS_MAP.get(task.isDone())
            + " "
            + task.toString();
    }

    @Override
    public String getTaskListRepresentation(final TaskList tasks) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            ret.append(i + 1)
                .append(".")
                .append(getTaskRepresentation(tasks.getTask(i)))
                .append(System.lineSeparator());
        }
        return ret.toString();
    }

    /**
     * Initialises the MainWindow.
     */
    @FXML
    public void initialize() {
        try {
            storage.loadTasks(tasks);
        } catch (DukeStorageException e) {
            showWarning(e.getMessage());
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
