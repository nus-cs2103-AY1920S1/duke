package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public abstract class Command {
    protected boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(Storage storage, Ui ui, TaskList tasklist) throws DukeException;
}
