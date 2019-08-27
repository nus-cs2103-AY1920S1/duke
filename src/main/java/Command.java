package duke.command;

import duke.task.TaskList;
import duke.exception.*;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }
}