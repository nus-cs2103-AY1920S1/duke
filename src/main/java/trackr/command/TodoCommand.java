package trackr.command;

import trackr.exception.TrackrException;
import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.task.Todo;
import trackr.tasklist.TaskList;

/**
 * Class when user issues a Todo command.
 */
public class TodoCommand extends Command {

    /**
     * Input from user.
     */
    private String userInput;

    /**
     * Class constructor that assigns instance with user input.
     * @param userInput Input from user.
     */
    public TodoCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Adds a Todo task to the task list.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks input history
     * @return String Informs user that Todo task has been added
     * @throws TrackrException When task description given by user is empty
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history) throws TrackrException {
        String result = "";
        String[] inputStringArr = userInput.split(" ");
        if (inputStringArr.length > 1) {
            String taskName = ((userInput.split(" ", 2))[1]).strip();
            Todo t = new Todo(taskName);
            tasks.add(t);
            result += "Got it. I've added this task:\n" + "       [T]" + "[ ]" + ' ' + t + '\n'
                    + "Now you have " + tasks.size() + " tasks in the list.";
            storage.appendToFile("T | 0 | " + taskName + '\n');
        } else {
            throw new TrackrException(":( OOPS!!! The description of a todo cannot be empty.");
        }
        return result;
    }
}
