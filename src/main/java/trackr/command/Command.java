package trackr.command;

import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.tasklist.TaskList;

/**
 * The Command class is an abstract class for all valid commands. All valid commands inherits from this class.
 */

public abstract class Command {

    /**
     * This is an abstract method that executes the correct actions based on the command.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     */
    public abstract String execute(TaskList tasks, Storage storage, HistoryTracker history);
}
