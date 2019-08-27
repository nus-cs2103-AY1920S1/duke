package duke.command;
import duke.tasklist.TaskList;

import duke.ui.DukeUi;

import duke.storagedata.StorageData;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    public void execute(TaskList tasks, DukeUi ui, StorageData storage) {
        ui.printByeMessage();
        System.exit(0);
    }
}
