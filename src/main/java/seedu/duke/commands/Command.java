package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.UI;

public abstract class Command {

    public boolean isExit = false;

    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        return "";
    }

}
