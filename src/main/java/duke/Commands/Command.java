package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
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
     * Execute the command on target task list and print command information through target user end.
     * @param tl The target task list to accept execution.
     * @param ui The target user end to print command information.
     * @throws DukeException
     */
    public abstract void execute(TaskList tl, Ui ui) throws DukeException;

    /**
     * Determines whether this is an exit command.
     * @return boolean, true if the specific command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
