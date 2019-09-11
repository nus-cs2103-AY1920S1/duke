package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Defines the two basic methods, execute and isExit, that all commands inheriting from this class
 * must implement.
 */
public abstract class Command {

    /**
     * Executes a command (based on the command type and the current status of the TaskList, Ui, and Storage).
     *
     * @param tasks List of Tasks.
     * @param ui Ui instance.
     * @param storage Storage instance that holds access to file in hard drive.
     * @throws DukeException If there is a problem in the format of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean based on whether a user command requires the Duke Bot to exit or not.
     *
     * @return Boolean that is true only for the bye command.
     */
    public abstract boolean isExit();

}
