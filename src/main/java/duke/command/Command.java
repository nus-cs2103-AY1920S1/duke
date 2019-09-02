package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    boolean isExit = false;

    /**
     * Returns true if the command triggers termination of the program.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command and return the response message as a String.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}