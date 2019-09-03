package duke.command;

import duke.exception.DukeExecutionException;
import duke.exception.DukeInvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    protected abstract void check(final TaskList tasks) throws DukeInvalidCommandException;

    /**
     * Executes this command.
     *
     * @param tasks   the existing tasks
     * @param storage the data file
     * @return CommandResult the execution result of the command
     * @throws DukeExecutionException if the command cannot be executed
     */
    public abstract CommandResult execute(TaskList tasks, Storage storage) throws DukeExecutionException;
}
