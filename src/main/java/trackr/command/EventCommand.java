package trackr.command;

import trackr.date.DateTime;
import trackr.storage.Storage;
import trackr.task.Event;
import trackr.tasklist.TaskList;
import trackr.ui.Ui;

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
     * Adds an Event task to the task list.
     * @param tasks List of tasks
     * @param ui Deals with interactions with the user
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String result = "";
            String[] separateTaskDate = userInput.split("/", 2);
            String taskName = ((separateTaskDate[0].split(" ", 2))[1]).strip();
            String date = ((separateTaskDate[1].split(" ", 2))[1]).strip();
            DateTime dt = new DateTime(date);
            Event e = new Event(taskName, dt.toString());
            assert e != null : "e should not be null";
            tasks.add(e);
            result += "Got it. I've added this task: \n" + "       [E][ ] " + e + " (at: " + dt + ")\n"
                    + "     Now you have " + tasks.size() + " tasks in the list.";
            storage.appendToFile("E | 0 | " + taskName + " | " + date + '\n');
            return result;
        } catch (ParseException e) {
            return ":( OOPS!!! Date provided is not in the correct format. Please provide date in this " +
                    "format: dd/MM/yyyy HHmm";
        }
    }
}
