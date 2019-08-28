package duke.command;

import duke.storage.Storage;
import duke.tasklist.MyList;
import duke.ui.UserInterface;

public class ExitCommand extends Command {

    @Override
    public void execute(MyList taskList, UserInterface ui, Storage storage) {
        ui.printExitMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
