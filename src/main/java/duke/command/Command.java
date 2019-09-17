package duke.command;

import duke.exception.DukeException;

import duke.module.CommandStack;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

/**
 * Abstract class to represent the commands supported by Duke.
 */
public abstract class Command {

    /**
     * Executes a certain function.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes if necessary.
     * @throws DukeException When applicable.
     */
    public abstract void execute(TaskList taskList, CommandStack commandStack, Ui ui, Storage storage)
            throws DukeException;

    /**
     * Returns the result of executing a certain function.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes.
     * @return Result of executing this command.
     * @throws DukeException When applicable.
     */
    public abstract String getResponse(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeException;

    /**
     * Shows whether executing this Command should quit the Duke or not.
     *
     * @return True if Duke should quit, false otherwise.
     */
    public abstract boolean shouldExit();

}
