package trackr.command;

import trackr.exception.TrackrException;
import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.task.Task;
import trackr.tasklist.TaskList;

/**
 * Class when user issues a List command.
 */
public class ListCommand extends Command {

    /**
     * Input from user.
     */
    private String userInput;

    /**
     * Class constructor that assigns instance with user input.
     * @param userInput Input from user.
     */
    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Prints out current lists of tasks.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Trakcs input history
     * @throws TrackrException When list command is not input correctly
     * @return String List of all tasks in the program
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history) throws TrackrException {
        String result = "";
        String[] inputStringArr = userInput.split(" ");
        if (inputStringArr.length > 1) {
            throw new TrackrException(":( OOPS!!! The list command should not be followed by other "
                    + "text!");
        } else {
            result += "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                int listNum = i + 1;
                Task t = tasks.get(i);
                result += "       " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                        + t.toString();
                if (t.getType().equals("todo")) {
                    result += '\n';
                } else if (t.getType().equals("event")) {
                    result += " (at: " + t.getDate() + ")" + '\n';
                } else {
                    result += " (by: " + t.getDate() + ")" + '\n';
                }
            }
        }
        return result;
    }
}
