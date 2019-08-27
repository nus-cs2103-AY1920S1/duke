package myduke.command;

import myduke.core.StorageManager;
import myduke.core.Ui;
import myduke.exception.DukeException;
import myduke.task.Task;
import myduke.task.TaskList;

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
        ui.printResponse("Noted. I've removed this task:", deletedTask, taskList.size());
    }
}
