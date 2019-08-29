package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Returns true if the command triggers termination of the program.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}