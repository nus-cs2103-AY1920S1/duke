package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

/**
 * Generic command class, to be inherited by specific commands.
 */
public abstract class Command {
    int pos;
    protected Task task;
    boolean exit = false;

    public abstract void execute(TaskList t, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.exit;
    }
}

