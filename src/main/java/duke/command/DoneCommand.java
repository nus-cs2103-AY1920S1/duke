package duke.command;

import duke.exception.DukeExecutionException;
import duke.exception.DukeStorageException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.TASK_ALREADY_DONE;
import static duke.ui.Messages.TASK_MARKED_AS_DONE;

public class DoneCommand extends CommandWithNumber {
    public DoneCommand(final Integer taskNumber) {
        super(taskNumber);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeExecutionException {
        check(tasks);
        Task task = tasks.getTask(this.taskNumber);
        if (task.isDone()) {
            ui.showMessage(TASK_ALREADY_DONE);
        } else {
            task.markAsDone();
            ui.showMessage(TASK_MARKED_AS_DONE);
            ui.showIndented(task.toString());
            try {
                storage.writeTasks(tasks);
            } catch (DukeStorageException e) {
                ui.showWarning(e.getMessage());
            }
        }
    }
}
