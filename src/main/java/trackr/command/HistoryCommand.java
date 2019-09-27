package trackr.command;

import trackr.exception.TrackrException;
import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.tasklist.TaskList;

public class HistoryCommand extends Command {

    /**
     * Lists out user input history for the current session.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks input history
     * @return String User input history
     * @throws TrackrException When there is no input history
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history) throws TrackrException {
        String result = "";
        for (String input : history.getInputHistory()) {
            result += input + '\n';
        }
        if (result.isEmpty()) {
            throw new TrackrException(":( OOPS!!! The input history is empty.");
        } else {
            return result;
        }
    }
}
