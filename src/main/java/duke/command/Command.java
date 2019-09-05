package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents an abstract command that instructs Duke.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks List of tasks
     * @param ui User-Interface
     * @param storage Storage object
     * @return Duke's response as a string.
     * @throws DukeException If something goes wrong.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean checkExit();
}
