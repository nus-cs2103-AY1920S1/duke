package dose.command;

import dose.task.Task;
import dose.task.TaskList;
import dose.util.Storage;
import dose.util.Ui;
import dose.util.UiMessage;
import dose.util.exception.DoseException;

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
     * @throws DoseException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoseException {
        Task task = getTaskById(tasks);
        task.markAsDone();

        String message = UiMessage.TASK_DONE.getMessage() + task.toString();
        ui.showMessage(message);
    }
}
