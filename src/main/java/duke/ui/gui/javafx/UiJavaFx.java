package duke.ui.gui.javafx;

import duke.model.Task;
import duke.ui.Ui;

import java.util.List;
import java.util.StringJoiner;

public class UiJavaFx extends Ui {
    private final MainWindow mainWindowController;
    private StringJoiner output = createStringJoiner();

    public UiJavaFx(MainWindow mainWindowController) {
        super();
        this.mainWindowController = mainWindowController;
    }

    private void showMessageToUser() {
        final String dukeOutput = output.toString();
        output = createStringJoiner();
        this.mainWindowController.addDukeMessage(dukeOutput);
    }

    @Override
    public void println() {
        output.add("");
    }

    @Override
    public void println(String content) {
        output.add(content);
    }

    @Override
    public void printBlock(String content) {
        println(content);
    }

    @Override
    public void displayWelcome() {
        super.displayWelcome();
        showMessageToUser();
    }

    @Override
    public void displayError(String message) {
        super.displayError(message);
        showMessageToUser();
    }

    @Override
    public void displayTasks(String title, List<Task> tasks) {
        super.displayTasks(title, tasks);
        showMessageToUser();
    }

    @Override
    public void displaySuccessfullyDoneTask(String title, Task task) {
        super.displaySuccessfullyDoneTask(title, task);
        showMessageToUser();
    }

    @Override
    public void displaySuccessfullyRemovedTask(String title, Task task, int tasksLeft) {
        super.displaySuccessfullyRemovedTask(title, task, tasksLeft);
        showMessageToUser();
    }

    @Override
    public void displaySuccessfullyAddedTask(String title, Task task, int tasksLeft) {
        super.displaySuccessfullyAddedTask(title, task, tasksLeft);
        showMessageToUser();
    }
}
