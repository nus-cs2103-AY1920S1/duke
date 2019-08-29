package Commands;

import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
