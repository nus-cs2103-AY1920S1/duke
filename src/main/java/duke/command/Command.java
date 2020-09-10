package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param storage The Storage for saving tasks to file.
     * @return The response string.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Checks if it is a bye command.
     *
     * @return A boolean: true if it is a bye command.
     */
    public abstract boolean isExit();

}
