package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a new task and saves the program state.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert task != null : "Task should not be null";

        tasks.addTask(task);
        storage.save(tasks);

        ui.printLine("Got it. I've added this task: ");
        ui.printLine(task);
        ui.printLine("Now you have "
                + tasks.size()
                + " tasks in the list.");
    }
}
