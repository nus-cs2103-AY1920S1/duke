package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Class representing a command that deletes a specified task from
 * the stored list in Duke.
 */
public class DeleteCommand extends Command {
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
     * @throws DukeException if taskIndex is out of bounds or refers to the index
     *                       of a non-existent Task
     */
    public String execute() throws DukeException {
        Task task = this.taskList.delete(taskId);
        //this.ui.displaySingleLine("Noted. I've removed this task:");
        //this.ui.displayMessage(task.toString(), 2);
        //this.ui.displaySingleLine("Now you have "
        //        + this.taskList.getSize()
        //        + " task(s) in the list.");
        this.storage.saveToDisk(this.taskList);
        return "Noted. I've removed this task:\n"
                + ui.indentMessage(task.toString())
                + "Now you have " + this.taskList.getSize() + " task(s) in the list.";
    }
}
