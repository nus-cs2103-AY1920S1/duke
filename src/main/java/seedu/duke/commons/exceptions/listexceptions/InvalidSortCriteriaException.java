package seedu.duke.commons.exceptions.listexceptions;

import seedu.duke.commons.exceptions.DukeException;

/**
 * Defines a InvalidListIndexException object that extends the DukeException class.
 * Signals that a given sort criteria is in the wrong format.
 */
public class InvalidSortCriteriaException extends DukeException {

    /**
     * Creates an instance of InvalidListIndexException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidSortCriteriaException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidSortCriteriaException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidSortCriteriaException(String message, Throwable cause) {
        super(message, cause);
    }
}
