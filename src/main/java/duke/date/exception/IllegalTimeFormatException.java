package duke.date.exception;

import duke.exception.DukeException;

/**
 * Encapsulate the exception thrown when user time input is not recognised.
 */
public class IllegalTimeFormatException extends DukeException {

    /**
     * Constructs the IllegalTimeFormatException object.
     *
     * @param msg Message of the exception.
     */
    public IllegalTimeFormatException(String msg) {
        super(msg);
    }
}
