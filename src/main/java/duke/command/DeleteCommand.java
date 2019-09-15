package duke.command;

import duke.exception.DukeExecutionException;
import duke.exception.DukeStorageException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends SingleTaskCommand {
    public DeleteCommand(final Integer taskNumber) {
        super(taskNumber);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeExecutionException {
        check(tasks);
        Task task = tasks.deleteTask(this.taskNumber);
        ui.deleteTaskSuccess(task, tasks);
        try {
            storage.writeTasks(tasks);
        } catch (DukeStorageException e) {
            ui.showWarning(e.getMessage());
        }
    }
}
