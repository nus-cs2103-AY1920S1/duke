package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Class representing a command that deletes a specified task from
 * the stored list in Duke.
 */
public class DeleteCommand extends Command {

    public static final String KEYWORD = "delete";

    private int taskId;

    /**
     * Returns a DeleteCommand that executes the deletion of the task
     * specified by taskId.
     * @param taskId index of the task to be deleted. Uses 1-indexing
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Deletes the Task object specified by the input taskId supplied during
     * initialization.
     *
     * @return result feedback of the command to be printed to the user
     * @throws DukeException if taskIndex is out of bounds or refers to the index
     *                       of a non-existent Task
     */
    public String execute() throws DukeException {
        Task task = this.taskList.delete(taskId);
        this.storage.saveToDisk(this.taskList);
        return "Noted. I've removed this task:\n"
                + ui.indentMessage(task.toString())
                + "\nNow you have " + this.taskList.getSize() + " task(s) in the list.";
    }
}
