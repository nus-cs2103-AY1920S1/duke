package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

/**
 * Create an AddCommand. It adds tasks to user's list.
 */
public class AddCommand extends Command {
    public AddCommand(Task task) {
        super.task = task;
    }

    /**
     * Adds a single task to user's list.
     *
     * @param t TaskList to be appended.
     * @param ui UI to interact with user.
     * @param storage Storage to read and write files.
     */
    public void execute(TaskList t, Ui ui, Storage storage) {
        t.list.add(task);
        ui.showAddedTask(task, t.list.size());
    }
}