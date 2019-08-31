package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

public abstract class Command {


    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }

}
