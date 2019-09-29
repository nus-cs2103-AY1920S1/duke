package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import java.util.NoSuchElementException;

public abstract class AddTaskCommand implements Command {
    String restOfCommand;

    AddTaskCommand(String restOfCommand) {
        this.restOfCommand = restOfCommand;
    }

    abstract String getDescription();
    abstract String getDeadline();

    abstract Task createTask();

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = createTask();
            tasks.add(newTask);
        } catch (NoSuchElementException e) {
            // user input after task type is blank
            ui.showError("Oops! You did not enter a description!");
        }
    }
}
