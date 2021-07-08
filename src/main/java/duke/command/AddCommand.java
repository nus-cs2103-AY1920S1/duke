package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * AddCommand class adds a task to the list.
 *
 * @author scwaterbear
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Class Constructor.
     *
     * @param task task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        if (!isSaved(tasks, storage)) {
            return ui.showSaveError();
        }
        return ui.showAddTask(task,tasks.getSize());
    }
}
