package trackr.command;

import trackr.exception.TrackrException;
import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.task.Task;
import trackr.tasklist.TaskList;

public class UpdateCommand extends Command {
    /**
     * Input from user.
     */
    private String userInput;

    /**
     * Class constructor that assigns instance with user input.
     * @param userInput Input from user.
     */
    public UpdateCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Prints message that a task has been updated.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks input history
     * @throws TrackrException When task has already been marked done or number provided not in range
     * @throws NumberFormatException When the regex specified following the 'done' command is not an integer
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history)
            throws TrackrException, NumberFormatException {
        String result = "";
        String[] inputStringArr = userInput.split(" ", 3);
        if (inputStringArr.length > 1) {
            int taskNum;
            try {
                taskNum = Integer.parseInt(inputStringArr[1]);
            } catch (NumberFormatException e) {
                return ":( OOPS!!! The 'update' command requires you to input a number";
            }
            int totalTasks = tasks.size();
            if (taskNum < 1 || taskNum > totalTasks) {
                throw new TrackrException(":( OOPS!!! The number provided is not within the range of the "
                        + "list.");
            }
            if (inputStringArr.length > 2) {
                Task t = tasks.get(taskNum - 1);
                String taskName = t.toString();
                t.setName(inputStringArr[2]);
                result += "I've updated the following task from:\n"
                        + "       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + taskName;
                if (t.getType().equals("event")) {
                    result += " (at: " + t.getDate() + ")" + '\n';
                } else if (t.getType().equals("deadline")) {
                    result += " (by: " + t.getDate() + ")" + '\n';
                } else {
                    result += '\n';
                }
                result += "to:\n" + "       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t.toString();
                storage.rewriteFile(tasks);
            } else {
                throw new TrackrException(":( OOPS!! Please specify the new name of the task");
            }
        } else {
            throw new TrackrException(":( OOPS!!! Please specify the task's number that you would like to "
                    + "update.");
        }
        return result;
    }
}
