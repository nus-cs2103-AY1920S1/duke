package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import duke.util.UiMessage;
import duke.util.exception.DukeException;

public class DeleteCommand extends ModifyTaskCommand {

    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Deletes a task, based on command issued by the user.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = getTaskById(tasks);
        String taskDeletedMessage = UiMessage.TASK_DELETED + task.toString();

        tasks.deleteTask(task);

        ui.showMessage(taskDeletedMessage);
        String message = UiMessage.TASKS_STATUS_FRONT.getMessage()
                + tasks.getSize() + UiMessage.TASKS_STATUS_BACK.getMessage();
        ui.showMessage(message);
    }
}
