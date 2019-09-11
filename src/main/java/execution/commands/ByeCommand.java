package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;

public class ByeCommand extends Command {
    public ByeCommand(String bye) {
        super(bye);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        super.execute(taskList, ui, storage);
        checkValidity();

        ui.exit();

    }
}
