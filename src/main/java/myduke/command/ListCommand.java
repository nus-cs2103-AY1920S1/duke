package myduke.command;

import myduke.core.StorageManager;
import myduke.core.Ui;
import myduke.exception.DukeException;
import myduke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storage) throws DukeException {
        ui.printResponse("Here are the tasks in your list:", taskList);
    }
}
