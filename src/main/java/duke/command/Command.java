package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {

    private boolean isExit;

    Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }
}