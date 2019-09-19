package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.save(taskList.tasksToStringList(true));
        return ui.getExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
