package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String command, Task pending) {
        super(command, pending);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
