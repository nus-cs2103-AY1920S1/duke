package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

public class Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    public boolean isExit() {
        return false;
    }
}
