package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents command of the user.
 */
public abstract class Command {

    public abstract String execute(Storage storage, TaskList tasklist) throws DukeException;

}
