package Command;

import Utilities.Storage;
import Utilities.TaskList;
import Utilities.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
