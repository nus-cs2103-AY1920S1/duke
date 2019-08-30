package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.TASKS_COUNT;
import static duke.ui.Messages.TASK_DELETED;

public class DeleteCommand extends CommandWithNumber {
    public DeleteCommand(final Integer taskNumber) {
        super(taskNumber);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        check(tasks);
        Task task = tasks.deleteTask(this.taskNumber);
        ui.showMessage(TASK_DELETED);
        ui.showIndented(task.toString());
        ui.showMessage(String.format(TASKS_COUNT, tasks.size()));
        try {
            storage.writeTasks(tasks);
        } catch (DukeException e) {
            ui.showWarning(e.getMessage());
        }
    }
}
