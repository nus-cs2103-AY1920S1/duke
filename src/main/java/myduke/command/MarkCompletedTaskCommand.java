package myduke.command;

import myduke.core.StorageManager;
import myduke.core.Ui;
import myduke.exception.DukeException;
import myduke.task.Task;
import myduke.task.TaskList;

/**
 * Marks a task as completed.
 */
public class MarkCompletedTaskCommand extends ModifyListCommand {
    public MarkCompletedTaskCommand(int itemIndex) throws DukeException {
        super(itemIndex);
    }

    public MarkCompletedTaskCommand(String itemIndex) throws DukeException {
        super(itemIndex);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storage) throws DukeException {
        Task completedTask = taskList.markTaskAsDone(this.itemIndex);
        ui.printResponse("Nice! I've marked this task as done:", completedTask, -1);
    }
}
