package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Handles commands that require adding a task to the list of tasks, namely deadline, event, and todo.
 */
public class AddCommand extends Command {

    private Task addedTask;

    public AddCommand(Task t) {
        this.addedTask = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(addedTask);
        ui.printAddedTask(addedTask);
        ui.showNumTasks(tasks);
        try {
            storage.writeToFile(tasks);
        } catch (java.io.IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
