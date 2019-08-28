package duke.command;

import duke.main.*;
import duke.exception.DukeException;

public interface Command {
    public abstract void execute(Storage storage, Ui ui, TaskList tasks) throws DukeException;

    public abstract boolean isRunning();
}
