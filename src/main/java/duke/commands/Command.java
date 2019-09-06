package duke.commands;

import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.DukeException;

/**
 * The abstract super class for all commands.
 * This class will call corresponding methods in the TaskList class to carry out user's commands.
 * Note that the majority of DukeExceptions should be generated from this class, which means that
 *     Command class must check that the user's command is safe to execute before really send for
 *     execution. Otherwise, it should directly throw out a DukeException containing the error
 *     message.
 */

public abstract class Command {

    /**
     * Execute the command on target task list and print command information through target user interface.
     *
     * @param tl The target task list to accept execution.
     * @param ui The target user end to print command information.
     * @throws DukeException Based on requirements of a Command subclass.
     */
    public abstract String execute(TaskList tl, Ui ui) throws DukeException;

    /**
     * Determines whether this is an exit command.
     *
     * @return boolean, true if the specific command is an exit command, false otherwise.
     */
    public abstract boolean isExit();

    /**
     * This method checks that the task list and user interface to accept the command are not nulls.
     * If unfortunately they are, this method will exit the program with corresponding error message.
     *
     * @param tl The task list to accept the command.
     * @param ui The user interface to accept the command.
     */
    protected void checkNullPointer(TaskList tl, Ui ui) {
        assert tl != null: "This command is pointing to a null task list.";
        assert ui != null: "This command is pointing to a null user interface.";
    }
}
