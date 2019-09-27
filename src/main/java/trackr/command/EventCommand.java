package trackr.command;

import trackr.date.DateTime;
import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.task.Event;
import trackr.tasklist.TaskList;

import java.text.ParseException;

/**
 * Class when user issues a Deadline command.
 */
public class EventCommand extends Command {

    /**
     * Input from user.
     */
    private String userInput;

    /**
     * Class constructor that assigns instance with user input.
     * @param userInput Input from user.
     */
    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Adds an Event task to the task list and prints message informing user that the event has been added.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks input history
     * @return String Informs user that event task has been added to the list
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history) {
        try {
            String result = "";
            String[] separateTaskDate = userInput.split("/", 2);
            String taskName = ((separateTaskDate[0].split(" ", 2))[1]).strip();
            String date = ((separateTaskDate[1].split(" ", 2))[1]).strip();
            DateTime dt = new DateTime(date);
            Event e = new Event(taskName, dt.toString());
            tasks.add(e);
            result += "Got it. I've added this task: \n" + "       [E][ ] " + e + " (at: " + dt + ")\n"
                    + "     Now you have " + tasks.size() + " tasks in the list.";
            storage.appendToFile("E | 0 | " + taskName + " | " + date + '\n');
            return result;
        } catch (ParseException e) {
            return ":( OOPS!!! Date provided is not in the correct format. Please provide date in this "
                    + "format: dd/MM/yyyy HHmm";
        }
    }
}
