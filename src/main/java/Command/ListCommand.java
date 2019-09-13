package Command;

import Utilities.Storage;
import Utilities.TaskList;
import Utilities.Ui;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.ListCommand(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
