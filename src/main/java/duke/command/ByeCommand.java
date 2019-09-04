package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class ByeCommand extends Command {

    public ByeCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        ui.printBye();
        System.exit(0);
    }
}
