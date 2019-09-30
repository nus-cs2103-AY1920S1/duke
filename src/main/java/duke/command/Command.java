package duke.command;

import duke.exception.DukeExecutionException;
import duke.exception.DukeInvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Command base class.
 */
public abstract class Command {
    /**
     * Checks whether this Command is valid.
     * @param tasks the current tasks
     * @throws DukeInvalidCommandException if this Command is invalid
     */
    protected abstract void check(final TaskList tasks) throws DukeInvalidCommandException;

    /**
     * Executes this command.
     *
     * @param tasks   the existing tasks
     * @param ui      the ui to display command execution status
     * @param storage the data file
     * @throws DukeExecutionException if the command cannot be executed
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeExecutionException;

    /**
     * Returns true if Duke should exit after this command.
     *
     * @return true if Duke should exit after this command, or false otherwise
     */
    public boolean shouldExit() {
        return false;
    }
}
