package seedu.duke.commons.exceptions.dateandtimeexceptions;

import seedu.duke.commons.exceptions.DukeException;

/**
 * Defines a InvalidDateAndTimeException object that extends the DukeException class.
 * Signals that a given date or time to be parsed is in the wrong format.
 */
public class InvalidDateAndTimeException extends DukeException {

    /**
     * Creates an instance of InvalidDateAndTimeException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidDateAndTimeException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidDateAndTimeException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidDateAndTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
