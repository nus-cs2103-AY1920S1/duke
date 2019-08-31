package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Returns true or false depending whether application is terminated or not.
     *
     * @return boolean false by default.
     */
    public boolean isTerminated() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param taskList list of tasks.
     * @param ui user interface displaying the effects of the execution of the command.
     * @param storage local storage of data.
     */
    public abstract void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException;
}
