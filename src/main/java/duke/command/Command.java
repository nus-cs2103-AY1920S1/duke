package duke.command;

import duke.task.*;
import duke.ui.*;
import duke.storage.*;

public abstract class Command {

    public abstract void execute(TaskList tasklist, Ui ui, Storage storage);

    public abstract boolean isTerminated();

}
