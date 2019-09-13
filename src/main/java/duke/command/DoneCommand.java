package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Class representing a command that marks a task as done.
 * Inherits from the Command abstract class.
 *
 * @see Command
 */
public class DoneCommand extends Command {

    public static final String KEYWORD = "done";

    private int taskId;

    /**
     * Returns a DoneCommand object that executes the action of marking
     * a task with index taskId as done.
     *
     * @param taskId index of task to be marked as done. uses 1-indexing.
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Marks the specified task in the taskId attributed as done.
     *
     * @return result feedback of the command to be printed to the user
     * @throws DukeException if taskId refers to a non-existent task.
     */
    public String execute() throws DukeException {
        Task task = this.taskList.at(taskId);
        task.markAsDone();
        this.storage.saveToDisk(this.taskList);
        return "Nice! I've marked this task as done:\n"
                + ui.indentMessage(task.toString());
    }
}
