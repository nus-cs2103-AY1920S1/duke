package duke.command;

import duke.exception.DukeException;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

public abstract class Command {
    boolean isExit = false;
    public abstract void execute(Tasklist list, Ui ui) throws DukeException;

    public boolean isExit() {
        return isExit;
    }
}
