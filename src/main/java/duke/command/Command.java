package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList list of tasks.
     * @param storage local storage of data.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;
}
