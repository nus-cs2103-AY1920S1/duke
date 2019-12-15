package duke.command;

import duke.exception.DukeException;
import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A base class for Commands that other specific commands inherit from.
 */
public abstract class Command {
    protected String message = "";
    protected boolean isExit;
    protected boolean isUpdate;

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }
}
