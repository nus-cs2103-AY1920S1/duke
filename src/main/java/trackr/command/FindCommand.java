package trackr.command;

import trackr.exception.TrackrException;
import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.task.Task;
import trackr.tasklist.TaskList;

/**
 * Class when user issues a Find command.
 */
public class FindCommand extends Command {

    /**
     * Input from user.
     */
    private String userInput;

    /**
     * Class constructor that assigns instance with user input.
     * @param userInput Input from user.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Retrieves tasks matching the term provided by the user.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks input history
     * @return String Informs users which tasks match their search term
     * @throws TrackrException When no search term is provided
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history) throws TrackrException {
        String result = "";
        String[] inputStringArr = userInput.split(" ", 2);
        if (inputStringArr.length == 1) {
            throw new TrackrException(":( OOPS!!! Please enter your search term.");
        } else {
            String searchTerm = inputStringArr[1];
            result += "Here are the matching tasks in your list:";
            int listNum = 1;
            boolean isTaskFound = false;
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                String taskName = t.toString();
                if (taskName.contains(searchTerm)) {
                    isTaskFound = true;
                    if (t.getType().equals("todo")) {
                        result += "     " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + '\n';
                    } else if (t.getType().equals("event")) {
                        result += "     " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                                + t + " " + "(at: " + t.getDate() + ")" + '\n';
                    } else {
                        result += "     " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                                + t + " (by: " + t.getDate() + ")" + '\n';
                    }
                    listNum++;
                }
            }
            if (isTaskFound) {
                return result;
            } else {
                return "Sorry. There are no matching tasks in your list.";
            }
        }
    }
}
