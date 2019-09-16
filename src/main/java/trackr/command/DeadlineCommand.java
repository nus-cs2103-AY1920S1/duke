package trackr.command;

import trackr.date.DateTime;
import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.task.Deadline;
import trackr.tasklist.TaskList;

import java.text.ParseException;

/**
 * Class when user issues a Deadline command.
 */
public class DeadlineCommand extends Command {

    /**
     * Input from user.
     */
    private String userInput;

    /**
     * Class constructor that assigns instance with user input.
     * @param userInput Input from user.
     */
    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Adds a Deadline task to the task list.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history) {
        try {
            String result = "";
            String[] separateTaskDate = userInput.split("/", 2);
            String taskName = ((separateTaskDate[0].split(" ", 2))[1]).strip();
            String date = ((separateTaskDate[1].split(" ", 2))[1]).strip();
            DateTime dt = new DateTime(date);
            Deadline d = new Deadline(taskName, dt.toString());
            tasks.add(d);
            result += "Got it. I've added this task:\n" + "       [D][ ] " + d + " (by: " + dt + ")\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
            storage.appendToFile("D | 0 | " + taskName + " | " + dt.toString() + '\n');
            return result;
        } catch (ParseException e) {
            return ":( OOPS!!! Date provided is not in the correct format. Please provide date in this " +
                    "format: dd/MM/yyyy HHmm";
        }
    }
}
