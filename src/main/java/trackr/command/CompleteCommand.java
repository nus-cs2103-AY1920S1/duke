package trackr.command;

import trackr.exception.TrackrException;
import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.task.Task;
import trackr.tasklist.TaskList;

/**
 * Class when user issues a Done command.
 */
public class CompleteCommand extends Command {

    /**
     * Input from user.
     */
    private String userInput;

    /**
     * Class constructor that assigns instance with user input.
     * @param userInput Input from user.
     */
    public CompleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Prints message that a task has been marked as completed.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks input history
     * @throws TrackrException When task has already been marked done or number provided not in range
     * @throws NumberFormatException When the regex specified following the 'complete' command is not an
     *     integer
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history) throws TrackrException {
        String result = "";
        int taskNum;
        try {
            taskNum = getTaskNumber(userInput);
        } catch (NumberFormatException e) {
            return ":( OOPS!!! The 'complete' command requires you to input a number";
        }
        int totalTasks = tasks.size();
        if (isValidNumber(taskNum, totalTasks)) {
            result = markTestAsCompleted(taskNum, tasks, storage);
        } else {
            throw new TrackrException(":( OOPS!!! The number provided is not within the range of the list.");
        }
        return result;
    }

    /**
     * Extracts task number specified from user input.
     * @param input User input
     * @return int Task number
     * @throws NumberFormatException When user does not specify a number with the 'complete' command
     */
    private static int getTaskNumber(String input) throws NumberFormatException {
        String[] inputStringArr = input.split(" ");
        int taskNum;
        if (inputStringArr.length > 1) {
            taskNum = Integer.parseInt(inputStringArr[1]);
        } else {
            throw new TrackrException(":( OOPS!!! Please specify the completed task's number.");
        }
        return taskNum;
    }

    /**
     * Checks whether number specified by user is within the list.
     * @param taskNum Number specified by user
     * @param maxNum Number of last item in the list.
     * @return boolean True if number specified by user is valid, false otherwise
     */
    private static boolean isValidNumber(int taskNum, int maxNum) {
        return taskNum >= 1 && taskNum <= maxNum;
    }

    /**
     * Indicates that the task is completed.
     * @param taskNum Task number specified by user
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @return String Informs user whether the task was successfully marked as completed or
     * @throws TrackrException When task was already marked as completed
     */
    private static String markTestAsCompleted(int taskNum, TaskList tasks, Storage storage) throws TrackrException {
        Task t = tasks.get(taskNum - 1);
        boolean isDone = t.getStatus();
        if (isDone) {
            throw new TrackrException(":( OOPS!!! The task has already been marked as completed.");
        } else {
            t.setDone();
            storage.rewriteFile(tasks);
            return "Nice! I've marked this task as done:\n" + "       [" + '+' + "] " + t;
        }
    }
}
