package duke.command;
import duke.tasklist.TaskList;
import duke.ui.DukeUI;
import duke.storagedata.StorageData;
public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    public void execute(TaskList tasks, DukeUI ui, StorageData storage) {
        ui.printByeMessage();
        System.exit(0);
    }
}
