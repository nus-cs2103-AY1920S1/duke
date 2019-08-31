package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Class representing a command that marks a specified task
 * within the stored task list as done. Inherits from the Command abstract
 * class.
 * @see Command
 */
public class DoneCommand extends Command {
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
     * @throws DukeException if taskId refers to a non-existent task.
     */
    public void execute() throws DukeException {
        Task task = this.taskList.at(taskId);
        task.markAsDone();
        this.ui.displaySingleLine("Nice! I've marked this task as done:");
        this.ui.displayMessage(task.toString(), 2);
        this.storage.saveToDisk(this.taskList);
    }
}
