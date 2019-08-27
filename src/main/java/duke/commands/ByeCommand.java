package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

public class ByeCommand extends Command {

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showByeMessage();
    }

    public boolean isExit() {
        return true;
    }

}