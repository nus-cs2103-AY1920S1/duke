package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

/**
 * Represents a command.
 */
public abstract class Command {
    String commandOutput = "";
    boolean isExit = false;

    /**
     * Executes the command.
     * @param list List of tasks.
     * @param ui The user interface the user sees.
     * @param storage Stores the user's list of tasks.
     * @param history
     * @throws DukeException when an error occurs during execution.
     */
    public abstract void execute(Tasklist list, Ui ui, Storage storage, History history) throws DukeException;

    /**
     * Returns the output of executing the command.
     * @return The output of the command.
     */
    public String commandOutput() {
        return commandOutput;
    }

    /**
     * Returns the class name.
     * @return the class name.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
