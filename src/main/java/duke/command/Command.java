package duke.command;

import duke.DukeException;
import duke.ListStoreModel;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command to be executed by Duke. A <code>Command</code> corresponds
 * to a specific set of actions to be performed by Duke, which will lead to various
 * outcomes as specified by the execution of the command.
 */
public abstract class Command {
    /**
     * Returns true if the command is an exit command, else false.
     * @return true if the command is an exit command.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    /**
     * Performs the command.
     * @param lists taskList and peopleList given.
     * @param ui ui object given.
     * @param storage storage given.
     * @throws DukeException if the command is unable to execute completely.
     */
    public abstract void execute(ListStoreModel lists, Ui ui, Storage storage) throws DukeException;
}
