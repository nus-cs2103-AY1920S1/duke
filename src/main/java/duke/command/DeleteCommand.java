package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

import duke.task.Task;

/**
 * Represents a command to delete a task. The <code>DeleteCommand</code> class 
 * inherits from the <code>Command</code> class to represent user instruction 
 * to delete an existing task from the task list.
 */
public class DeleteCommand extends Command {

    /** The index of the task to be deleted, as specified by this command. */
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
     * UI, and storage (saving tasks in a file in hard disk). GUI version.
     *
     * @param tasks The task list where tasks are stored.
     * @param ui The user interface that interacts with user input.
     * @param storage The <code>Storage</code> object that handles task
     * @return A string that represents the result of this execution.
     * @throws DukeException If an exception occurs during execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.getTask(taskId);
        tasks.removeTask(taskId);
        storage.save(tasks);
        return ui.removedTask(t, tasks.getSize());
    }
}


