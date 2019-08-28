package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.MyList;
import duke.ui.UserInterface;

public abstract class Command {
    public abstract void execute(MyList taskList, UserInterface ui, Storage storage) throws DukeException;

    //default value of isExit
    public boolean isExit() {
        return false;
    }
}
