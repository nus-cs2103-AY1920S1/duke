package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
