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
    public String getResponse(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        tasklist.clearAll();
        storage.updateData(tasklist);
        assert tasklist.getList().isEmpty() : "ArrayList should be Empty";
        return ui.generateResponse("Your list is now empty.");
    }
}
