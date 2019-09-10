package trackr.command;

import trackr.exception.TrackrException;
import trackr.storage.Storage;
import trackr.task.Task;
import trackr.tasklist.TaskList;
import trackr.ui.Ui;

/**
 * Class when user issues a Done command.
 */
public class DoneCommand extends Command {

    /**
     * Input from user.
     */
    private String userInput;

    /**
     * Class constructor that assigns instance with user input.
     * @param userInput Input from user.
     */
    public DoneCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Prints message that a task has been marked as completed.
     * @param tasks List of tasks
     * @param ui Deals with interactions with the user
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @throws TrackrException When task has already been marked done or number provided not in range
     * @throws NumberFormatException When the regex specified following the 'done' command is not an integer
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TrackrException, NumberFormatException {
        String result = "";
        String[] inputStringArr = userInput.split(" ");
        if (inputStringArr.length > 1) {
            int taskNum;
            try {
                taskNum = Integer.parseInt(inputStringArr[1]);
            } catch (NumberFormatException e) {
                return ":( OOPS!!! The 'done' command requires you to input a number";
            }
            int totalTasks = tasks.size();
            if (taskNum < 1 || taskNum > totalTasks) {
                throw new TrackrException(":( OOPS!!! The number provided is not within the range of the "
                        + "list.");
            } else {
                Task t = tasks.get(taskNum - 1);
                boolean isDone = t.getStatus();
                if (isDone) {
                    throw new TrackrException(":( OOPS!!! The task has already been marked as completed.");
                } else {
                    t.setDone();
                    result += "Nice! I've marked this task as done:\n" + "       [" + '+' + "] " + t;
                    storage.rewriteFile(tasks);
                }
            }
        } else {
            throw new TrackrException(":( OOPS!!! Please specify the completed task's number.");
        }
        return result;
    }
}
