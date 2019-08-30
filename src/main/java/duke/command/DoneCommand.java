package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends CommandWithNumber {
    public DoneCommand(final Integer taskNumber) {
        super(taskNumber);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        check(tasks);
        Task task = tasks.getTask(this.taskNumber);
        if (task.isDone()) {
            ui.showMessage("This task is already done");
        } else {
            task.markAsDone();
            storage.writeTasks(tasks);
            ui.showMessage("Nice! I've marked this duke.task as done:");
            ui.showIndented(task.toString());
        }
    }
}
