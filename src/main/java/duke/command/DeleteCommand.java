package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.task.Task;
import duke.core.TaskList;
import duke.core.Ui;

/**
 * Represents a command to delete a task. The <code>DeleteCommand</code> class 
 * inherits from the <code>Command</code> class to represent user instruction 
 * to delete an existing task from the task list.
 */
public class DeleteCommand extends Command {
    /** The index of the task to be deleted, as specifed by this command. */
    private int taskId;

    /**
     * Constructs a <code>DeleteCommand</code> object.
     *
     * @param taskId Specifies the index of the task to be deleted.
     */
    public DeleteCommand(int taskId) {
        super();
        this.taskId = taskId;
    }

    /**
     * Indicates whether this command tells Duke to exit.
     *
     * @return A boolean. True if the command tells Duke to exit, false 
     *          otherwise. Since the <code>DeleteCommand</code> is not a "bye", 
     *          the return value is set to false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command with respect to tasks (modifying the task list), 
     * UI, and storage (saving tasks in a file in hard disk).
     *
     * @param tasks The task list where tasks are stored.
     * @param ui The user interface that interacts with user input.
     * @param storage The <code>Storage</code> object that handles task 
     *      storage in local file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.getTask(taskId - 1);
        tasks.removeTask(taskId - 1);
        ui.removedTask(t, tasks.getSize());
        storage.save(tasks);
    }
}


