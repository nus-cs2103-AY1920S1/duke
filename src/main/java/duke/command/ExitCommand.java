package duke.command;

import duke.storage.DukeStorage;
import duke.tasklist.MyList;
import duke.ui.DukeUserInterface;

public class ExitCommand extends Command {

    @Override
    public void execute(MyList taskList, DukeUserInterface ui, DukeStorage storage) {
        ui.printExitMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
