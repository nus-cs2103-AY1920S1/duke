package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import duke.util.UiMessage;
import duke.util.exception.DukeException;

/**
 * Represents a request from the user to mark a given task as done.
 */
public class DoneCommand extends ModifyTaskCommand {

    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Marks the task in the command issued by the user as done.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = getTaskById(tasks);
        task.markAsDone();

        String message = UiMessage.TASK_DONE.getMessage() + task.toString();
        ui.showMessage(message);
    }
}
