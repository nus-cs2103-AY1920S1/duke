package duke.command;

import duke.exception.DukeException;

import java.util.Optional;

/**
 * Represents a command entered by a user.
 */
public abstract class Command {

    /**
     * Returns a String after a command has been executed.
     *
     * @param input the description of a command.
     * @return the String representation of the message to be printed after the execution.
     * @throws DukeException if a command fails to execute.
     */
    public abstract String execute(Optional<String[]> input) throws DukeException;

    /**
     * Returns a boolean of whether the command is a bye command. This method is overridden when the user
     * enters a bye command.
     *
     * @return the boolean of whether the command is a bye command.
     */
    public boolean isExit() {
        return false;
    }
}
