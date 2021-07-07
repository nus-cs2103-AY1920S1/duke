package myduke.command;

import myduke.storage.StorageManager;
import myduke.ui.Ui;
import myduke.exception.DukeException;
import myduke.task.Task;
import myduke.task.TaskList;

/**
 * Deletes a task from a list of tasks.
 */
public class DeleteCommand extends ModifyListCommand {
    public DeleteCommand(int itemIndex) throws DukeException {
        super(itemIndex);
    }

    public DeleteCommand(String itemIndex) throws DukeException {
        super(itemIndex);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(this.itemIndex);
        ui.printResponse("Noted. I've removed this task:", taskList.size(), deletedTask);
    }
}
