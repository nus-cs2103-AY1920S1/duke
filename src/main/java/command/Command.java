package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;

/**
 * Represents a Command that Parser returns to the main logic in Duke.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
