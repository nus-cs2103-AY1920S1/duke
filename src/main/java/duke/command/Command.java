package duke.command;

import duke.exception.DukeExecutionException;
import duke.exception.DukeInvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    /**
     * Returns true if the command signals exit.
     *
     * @return true if the command signals exit
     */
    public boolean isExit() {
        return false;
    }

    protected abstract void check(final TaskList tasks) throws DukeInvalidCommandException;

    /**
     * Executes this command.
     *
     * @param tasks   the existing tasks
     * @param ui      the current Ui
     * @param storage the data file
     * @throws DukeExecutionException if the command cannot be executed
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeExecutionException;
}
