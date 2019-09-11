package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;

public class ListCommand extends Command {
    public ListCommand(String text) {
        super(text);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        super.execute(taskList, ui, storage);
        checkValidity();

        ui.listTasks(taskList);
    }

}
