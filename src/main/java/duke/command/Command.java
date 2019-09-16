package duke.command;

import duke.exception.DukeException;
import duke.task.*;
import duke.ui.*;
import duke.storage.*;

public abstract class Command {

    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isTerminated();

}
