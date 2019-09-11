package trackr.command;

import trackr.storage.Storage;
import trackr.task.Task;
import trackr.tasklist.TaskList;
import trackr.ui.Ui;

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
     * @param ui Deals with interactions with the user
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NumberFormatException {
        String result = "";
        String[] inputStringArr = userInput.split(" ");
        int taskNum = Integer.parseInt(inputStringArr[1]);
        Task t = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        result += "Noted. I've removed this task:\n";
        if (t.getType().equals("todo")) {
            result += "       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t +'\n';
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
