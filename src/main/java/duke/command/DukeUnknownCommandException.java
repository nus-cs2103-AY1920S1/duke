package duke.command;

import duke.DukeException;

/**
 * Exception to be thrown when the command provided is not a supported command.
 */
public class DukeUnknownCommandException extends DukeException {

    /**
     * Constructs the exception to be thrown when the command provided is not supported.
     */
    public DukeUnknownCommandException() {
        super("I'm sorry. I don't know what that means :c");
    }
}
