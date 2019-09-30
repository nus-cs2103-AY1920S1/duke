package dose.command;

import dose.task.TaskList;
import dose.util.Storage;
import dose.util.Ui;
import dose.util.UiMessage;
import dose.util.exception.DoseException;
import dose.util.exception.ExceptionType;

public class ListCommand implements Command {

    /**
     * Lists all tasks currently in the list.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     * @throws DoseException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoseException {
        if (tasks.isEmpty()) {
            throw new DoseException(ExceptionType.TASK_LIST_EMPTY);
        }
        ui.showMessage(UiMessage.TASK_LIST);
        ui.showTasks(tasks);
    }
}
