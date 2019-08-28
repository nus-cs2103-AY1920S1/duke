package duke.command;

import duke.task.Task;
import duke.core.TaskList;
import duke.core.Ui;
import duke.core.Storage;
import duke.core.DukeException;

/**
 * Represents a command to mark a task as done. The <code>DoneCommand</code> 
 * class inherits from the <code>Command</code> class to represent user 
 * instruction to mark an existing task from the task list as completed.
 */
public class DoneCommand extends Command {
    /**
     * The index of the task to be marked as done, as specifed by this command.
     */
    private int taskId;

    /**
     * Constructs a <code>DoneCommand</code> object.
     * @param taskId Specifies the index of the task to be marked as done.
     */
    public DoneCommand(int taskId) {
        super();
        this.taskId = taskId;
    }

    /**
     * Indicates whether this command tells Duke to exit. 
     * @return A boolean. True if the command tells Duke to exit, false 
     *          otherwise. Since the <code>DoneCommand</code> is not a "bye", 
     *          the return value is set to false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command with respect to tasks (modifying the task list), 
     * UI, and storage (saving tasks in a file in hard disk). 
     * @param tasks The task list where tasks are stored.
     * @param ui The user interface that interacts with user input.
     * @param storage The <code>Storage</code> object that handles task 
     *      storage in local file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.getTask(taskId - 1);
        t.markAsDone();
        storage.save(tasks);
        ui.markedAsDone(t);
    }
}
