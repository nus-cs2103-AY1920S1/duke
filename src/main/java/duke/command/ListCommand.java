package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

/**
 * Represents a command to list all existing tasks in the task list. The 
 * <code>ListCommand</code> class inherits from the <code>Command</code> class
 * to represent user instruction to display all existing tasks in the task list. 
 */
public class ListCommand extends Command {

    /**
     * Constructs a <code>ListCommand</code> object.
     */
    public ListCommand() {
        super();
    }

    /**
     * Indicates whether this command tells Duke to exit.
     *
     * @return A boolean. True if the command tells Duke to exit, false 
     *          otherwise. Since the <code>ListCommand</code> is not a "bye", 
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
    }

    /**
     * Executes the command with respect to tasks (modifying the task list),
     * UI, and storage (saving tasks in a file in hard disk). GUI version.
     *
     * @param tasks The task list where tasks are stored.
     * @param ui The user interface that interacts with user input.
     * @param storage The <code>Storage</code> object that handles task
     * @return A string that represents the result of this execution.
     */
    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) {
        return ui.printTasksGui(tasks);
    }
}
