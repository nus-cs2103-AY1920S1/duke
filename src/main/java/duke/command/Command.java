package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.Storage;

public abstract class Command {

    protected boolean isExit = false;
    protected String[] description;

    protected Command(String[] description) {
        this.description = description;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
