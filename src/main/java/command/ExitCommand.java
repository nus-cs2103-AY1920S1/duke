package command;

import exception.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.save(taskList.getList());
        ui.print("Bye. Hope to see you again soon!");
    }
}
