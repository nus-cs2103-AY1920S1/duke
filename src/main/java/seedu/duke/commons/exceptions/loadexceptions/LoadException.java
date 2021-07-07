package seedu.duke.commons.exceptions.loadexceptions;

import seedu.duke.commons.exceptions.DukeException;

/**
 * Defines a InvalidListIndexException object that extends the LoadException class.
 * Signals an exception that occurred while loading the file from Storage.
 */
public class LoadException extends DukeException {

    /**
     * Creates an instance of InvalidListIndexException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public LoadException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidListIndexException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public LoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
