package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.main.TaskList;
import duke.exception.DukeException;

public interface Command {
    public abstract void execute(Storage storage, Ui ui, TaskList tasks) throws DukeException;

    public abstract boolean isRunning();
}
