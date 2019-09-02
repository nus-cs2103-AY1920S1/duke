package duke.command;

import duke.exception.DukeException;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

/**
 * Abstract class to represent the commands supported by Duke.
 */
abstract public class Command {

    /**
     * Executes a certain function.
     *
     * @param taskList List of tasks to manage.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes if necessary.
     * @throws DukeException When applicable.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Shows whether executing this Command should quit the Duke or not.
     *
     * @return True if Duke should quit, false otherwise.
     */
    public abstract boolean isExit();

}
