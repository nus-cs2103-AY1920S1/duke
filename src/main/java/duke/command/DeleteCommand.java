package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends CommandWithNumber {
    public DeleteCommand(final Integer taskNumber) {
        super(taskNumber);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        check(tasks);
        Task task = tasks.deleteTask(this.taskNumber);
        storage.writeTasks(tasks);
        ui.showMessage("Noted. I've removed this duke.task:");
        ui.showMessage("  " + task);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list");
    }
}
