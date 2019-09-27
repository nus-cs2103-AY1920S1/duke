package trackr.command;

import trackr.exception.TrackrException;
import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.task.Task;
import trackr.tasklist.TaskList;

/**
 * Class when user issues a Delete command.
 */
public class RemoveCommand extends Command {

    /**
     * Input from user.
     */
    private String userInput;

    /**
     * Class constructor that assigns instance with user input.
     * @param userInput Input from user.
     */
    public RemoveCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Deletes task from list based in index provided by user.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks input history
     * @return String Inform user that task has been removed
     * @throws TrackrException When user does not specify task number for the task to be removed
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history) throws TrackrException {
        String result = "";
        String[] inputStringArr = userInput.split(" ");
        int taskNum;
        try {
            taskNum = Integer.parseInt(inputStringArr[1]);
        } catch (NumberFormatException e) {
            throw new TrackrException(":( OOPS!!! The 'remove' command requires you to input a number");
        }
        Task t = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        result += "Noted. I've removed this task:\n";
        if (t.getType().equals("todo")) {
            result += "       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + '\n';
        } else if (t.getType().equals("event")) {
            result += "       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + " (at: "
                    + t.getDate() + ")\n";
        } else {
            result += "       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + " (by: "
                    + t.getDate() + ")\n";
        }
        result += "Now you have " + tasks.size() + " tasks in the list.";
        storage.rewriteFile(tasks);
        return result;
    }
}
