package duke.commands;

import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.DukeException;

/**
 * The abstract super class for all duke commands. It defines the two basic features about all
 * duke commands:
 * 1. Call the task list and the user interface to act according to the command.
 * 2. Decide whether this command is an exit command or not.
 * 3. Ensure that the task list and the user interface the command calls is not a null pointer.
 */

public abstract class Command {

    /**
     * Call the task list and the user interface to act according to the command.
     *
     * @param tl The target task list to accept the command.
     * @param ui The target user interface to generate the command information as a String.
     * @throws DukeException Based on specific Command subclass.
     */
    public abstract String execute(TaskList tl, Ui ui) throws DukeException;

    /**
     * Determines whether this is an exit command.
     *
     * @return boolean, true if the specific command is an exit command, false otherwise.
     */
    public abstract boolean isExit();

    /**
     * This method checks that the task list and user interface to accept the command are not null pointers.
     * If unfortunately they are, this method will exit the program with corresponding error message.
     *
     * @param tl The task list to accept the command.
     * @param ui The user interface to accept the command.
     */
    void checkNullPointer(TaskList tl, Ui ui) {
        assert tl != null : "This command is pointing to a null task list.";
        assert ui != null : "This command is pointing to a null user interface.";
    }
}
