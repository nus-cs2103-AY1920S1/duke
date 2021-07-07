package seedu.duke.commons.exceptions.listexceptions;

import seedu.duke.commons.exceptions.DukeException;

/**
 * Defines a InvalidListIndexException object that extends the DukeException class.
 * Signals that a given list index is in the wrong format, or given list index exceeds the size of the list.
 */
public class InvalidListIndexException extends DukeException {

    /**
     * Creates an instance of InvalidListIndexException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidListIndexException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidListIndexException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidListIndexException(String message, Throwable cause) {
        super(message, cause);
    }
}
