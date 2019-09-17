package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a Task as done.
 */
public class DoneCommand extends Command {

    /**
     * One-based index for the task number to be marked as complete.
     */
    private int taskNumToComplete;

    /**
     * Constructor for DoneCommand.
     *
     * @param taskNumToComplete One-based index of task number to mark as complete.
     */
    public DoneCommand(int taskNumToComplete) {
        super();
        this.taskNumToComplete = taskNumToComplete;
    }

    /**
     * Executes the DoneCommand
     * If the task number is outside the bounds of the TaskList, a DukeException is thrown.
     *
     * @param tasks   The user's current TaskList
     * @param ui      The ui currently being used by the user
     * @param storage The storage object being used by the user
     * @throws DukeException An error when trying to mark a task as done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskNumToComplete <= 0 || taskNumToComplete > tasks.getSize()) {
            throw new DukeException("Task number is out of bounds" + tasks.getSize());
        }
        tasks.markTaskAsDone(this.taskNumToComplete);
        Task taskToComplete = tasks.getTaskFromList(this.taskNumToComplete);
        String answer = ("Nice! I've marked this task as done:"
                + System.lineSeparator()
                + "  "
                + taskToComplete.getTaskStatus());
        ui.messageUser("Nice! I've marked this task as done:",
                "  " + taskToComplete.getTaskStatus());
        return answer;
    }
}
