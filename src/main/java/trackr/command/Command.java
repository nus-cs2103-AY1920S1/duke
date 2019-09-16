package trackr.command;

import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.tasklist.TaskList;

/**
 * The Command class is an abstract class for all valid commands. All valid commands inherits from this class.
 */

public abstract class Command {

    /**
     * Performs the action specified by the command and prints a message informing the user about the changes.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks input history
     * @return String Message informing user about changes by the command issued
     */
    public abstract String execute(TaskList tasks, Storage storage, HistoryTracker history);
}
