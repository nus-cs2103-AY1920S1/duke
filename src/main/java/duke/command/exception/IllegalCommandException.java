package duke.command.exception;

import duke.exception.DukeException;

/**
 * Encapsulate the exception thrown when user input is not recognised as a valid command.
 */
public class IllegalCommandException extends DukeException {

    public IllegalCommandException(String msg) {
        super(msg);
    }
}
