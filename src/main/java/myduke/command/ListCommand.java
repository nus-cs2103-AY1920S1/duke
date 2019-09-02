package myduke.command;

import myduke.storage.StorageManager;
import myduke.ui.Ui;
import myduke.exception.DukeException;
import myduke.task.TaskList;

/**
 * Lists the tasks present in a given task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storage) throws DukeException {
        ui.printResponse("Here are the tasks in your list:", taskList);
    }
}
