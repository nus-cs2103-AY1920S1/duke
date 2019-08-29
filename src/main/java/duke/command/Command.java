package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.storage.DukeStorage;
import duke.tasklist.MyList;
import duke.ui.DukeUserInterface;
import duke.ui.UserInterface;

public abstract class Command {
    public abstract void execute(MyList taskList, DukeUserInterface ui, DukeStorage storage) throws DukeException;

    //default value of isExit
    public boolean isExit() {
        return false;
    }
}
