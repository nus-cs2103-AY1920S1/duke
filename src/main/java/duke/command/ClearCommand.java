package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class ClearCommand extends Command {

    @Override
    public void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.clear();
        ui.println("Emptied task list.");
        storage.saveTaskListToFile(taskList);
    }
}
