package dose.command;

import dose.task.Task;
import dose.task.TaskList;
import dose.util.Storage;
import dose.util.Ui;
import dose.util.UiMessage;
import dose.util.exception.DoseException;

/**
 * Represents a command to postpone ("snooze") a task.
 */
public class SnoozeCommand extends ModifyTaskCommand {

    public SnoozeCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Snoozes the task in the command issued by the user.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     * @throws DoseException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoseException {
        Task task = getTaskById(tasks);
        task.snooze();
        ui.showMessage(UiMessage.TASK_SNOOZED);
    }
}
