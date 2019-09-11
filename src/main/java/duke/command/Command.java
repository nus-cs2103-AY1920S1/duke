package duke.command;

import duke.data.Storage;
import duke.object.TaskList;
import duke.user.Ui;

import java.io.IOException;

/**
 * Interface to be implemented by various user commands
 */
public abstract class Command {
    abstract public void execute (TaskList tasks, Ui ui, Storage storage) throws IOException;
    public abstract boolean isExit();
}
