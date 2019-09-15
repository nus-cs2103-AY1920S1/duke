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
    public String executeAsString(TaskList tasks, Ui ui, Storage storage) {
        return ui.ListCommandFX(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
