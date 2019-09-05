package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

/**
 * Represents a user command.
 */
public abstract class Command {

    protected TaskList tasks;
    protected Storage storage;
    protected boolean isExit;

    /**
     * Executes the {@link Command}.
     *
     * @throws DukeException If the command failed to execute.
     */
    public abstract CommandResult execute() throws DukeException, IOException;

    /**
     * Sets the data to be used by the {@link Command}.
     *
     * @param tasks   List of tasks.
     * @param storage Storage.
     */
    public void setData(TaskList tasks, Storage storage) {
        this.tasks = requireNonNull(tasks);
        this.storage = requireNonNull(storage);
    }

    /**
     * Returns true if the command will stop the {@link Duke} application.
     *
     * @return True if the command will stop the {@link Duke} application. False otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
