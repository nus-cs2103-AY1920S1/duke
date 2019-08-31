package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {

    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.showTable(task.list());
    }

    public boolean isExit() {
        return false;
    }

}
