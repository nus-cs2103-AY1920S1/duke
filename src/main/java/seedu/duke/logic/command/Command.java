package seedu.duke.logic.command;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Defines an abstract Command class.
 * Contains an executable method that all extensions of Command must include.
 * Contains a method defines whether or not the command should cause the program to exit.
 */
public abstract class Command {

    /** Executes a Command object using information stored in instance attributes.
     * Modifies a given Tasklist and Storage based on implementation of concrete class.
     * May possibly display a message indicating successful execution after process is completed, depending on
     * implementation of concrete class.
     * @param tasks TaskList to which a given task is to be modified by this command.
     * @param ui Ui with methods that are called to display a message upon successful exeuction of the task.
     * @param storage Storage that contains saved String representation of the given TaskList and is to be modified
     *                upon successful execution of the task.
     * @throws DukeException if execution of the command is unsuccessful.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether or not the Command instructs the program to exit.
     * Defines that a command causes the program to continue running unless overridden.
     * @return A boolean representing whether or not the Command instructs the program to exit.
     */
    public boolean isExit() {
        return false;
    }
}
