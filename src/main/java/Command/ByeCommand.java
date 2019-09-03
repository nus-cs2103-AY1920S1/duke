package Command;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {}

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
