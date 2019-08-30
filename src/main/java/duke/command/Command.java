package duke.command;

import duke.exception.DukeException;
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

    protected abstract void check(final TaskList tasks) throws DukeException;

    /**
     * Executes this command.
     *
     * @param tasks   the existing tasks
     * @param ui      the current Ui
     * @param storage the data file
     * @throws DukeException if the command cannot be executed
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
