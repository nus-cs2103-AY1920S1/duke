package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

import duke.task.Task;

/**
 * Represents a command to update an existing task in the task list. The
 * <code>UpdateCommand</code> class inherits from the <code>Command</code> class
 * to represent user instruction to edit an existing task.
 */
public class UpdateCommand extends Command {

    /** The index of the task to be deleted, as specified by this command. */
    private int taskId;

    /** The attribute that the user wants to update. */
    private String attribute;

    /** The new value to replace the existing one. */
    private String newValue;

    /**
     * Constructs a <code>UpdateCommand</code> object.
     *
     * @param taskId Specifies the index of the task to be deleted.
     * @param attribute Specifies the attribute that the user wants to update.
     * @param newValue The new value to replace the existing one.
     */
    public UpdateCommand(int taskId, String attribute, String newValue) {
        super();
        this.taskId = taskId;
        this.attribute = attribute;
        this.newValue = newValue;
    }

    /**
     * Indicates whether this command tells Duke to exit.
     *
     * @return A boolean. True if the command tells Duke to exit, false
     *          otherwise. Since the <code>UpdateCommand</code> is not a "bye",
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
     * @throws DukeException If an exception occurs during execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.updateTask(taskId, attribute, newValue);
        Task t = tasks.getTask(taskId);
        ui.updatedTask(t, tasks.getSize());
        storage.save(tasks);
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
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.updateTask(taskId, attribute, newValue);
        Task t = tasks.getTask(taskId);
        storage.save(tasks);
        return ui.updatedTaskGui(t, tasks.getSize());
    }
}
