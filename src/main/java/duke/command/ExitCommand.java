package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

/**
 * Represents a command to exit Duke. The <code>ExitCommand</code> class 
 * inherits from the <code>Command</code> class to represent user instruction 
 * to quit the running Duke program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a <code>ExitCommand</code> object.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Indicates whether this command tells Duke to exit.
     *
     * @return A boolean. True if the command tells Duke to exit, false 
     *          otherwise. Since the <code>ExitCommand</code> is a "bye", 
     *          the return value is set to true.
     */
    @Override
    public boolean isExit() {
        return true;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
