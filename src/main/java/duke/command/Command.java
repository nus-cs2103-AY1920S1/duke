package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command.
 */
public abstract class Command {

    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;
    protected boolean isExit;

    /**
     * Executes the {@link Command}.
     *
     * @throws DukeException If the command failed to execute.
     */
    public abstract void execute() throws DukeException;

    /**
     * Sets the data to be used by the {@link Command}.
     *
     * @param tasks List of tasks.
     * @param ui UI interface.
     * @param storage Storage.
     */
    public void setData(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
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
