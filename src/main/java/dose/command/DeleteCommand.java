package dose.command;

import dose.task.Task;
import dose.task.TaskList;
import dose.util.Storage;
import dose.util.Ui;
import dose.util.UiMessage;
import dose.util.exception.DoseException;

public class DeleteCommand extends ModifyTaskCommand {

    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Deletes a task, based on command issued by the user.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     * @throws DoseException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoseException {
        Task task = getTaskById(tasks);
        String taskDeletedMessage = UiMessage.TASK_DELETED.getMessage() + task.toString();

        tasks.deleteTask(task);

        ui.showMessage(taskDeletedMessage);
        String message = UiMessage.TASKS_STATUS_FRONT.getMessage()
                + tasks.getSize() + UiMessage.TASKS_STATUS_BACK.getMessage();
        ui.showMessage(message);
    }
}
