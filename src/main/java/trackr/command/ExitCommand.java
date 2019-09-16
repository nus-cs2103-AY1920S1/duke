package trackr.command;

import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.tasklist.TaskList;

/**
 * Class when user issues an Exit command.
 */
public class ExitCommand extends Command {

    /**
     * Calls the ui to execute actions for an exit command.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks input history
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history) {
        return "Bye. Hope to see you again soon!";
    }
}
