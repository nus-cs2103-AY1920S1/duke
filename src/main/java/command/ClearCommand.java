package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to clear all item in the TaskList.
 *
 */
public class ClearCommand extends Command {

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        tasklist.clearAll();
        ui.printStatement("Your list is now empty.");
        storage.updateData(tasklist);
    }
}
