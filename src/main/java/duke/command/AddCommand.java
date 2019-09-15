package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

import duke.task.Task;

/**
 * Represents a command to add a task. The <code>AddCommand</code> class 
 * inherits from the <code>Command</code> class to represent user instruction 
 * to add a new <code>NormalTask</code>, <code>Deadline</code> or <code>Event</code>
 * task.
 */
public class AddCommand extends Command {

    /** The task to be added, as specified by this command. */
    private Task task;

    /**
     * Constructs a <code>AddCommand</code> object.
     *
     * @param task Specifies the task to be added.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Indicates whether this command tells Duke to exit.
     *
     * @return A boolean. True if the command tells Duke to exit, false 
     *          otherwise. Since the <code>AddCommand</code> is not a "bye", 
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
        tasks.addTask(task);
        ui.addedTask(task, tasks.getSize());
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
        tasks.addTask(task);
        storage.save(tasks);
        return ui.addedTaskGui(task, tasks.getSize());
    }
}

