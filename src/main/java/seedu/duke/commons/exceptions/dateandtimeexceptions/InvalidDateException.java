package seedu.duke.commons.exceptions.dateandtimeexceptions;

/**
 * Defines a InvalidDateException object that extends the DukeException class.
 * Signals that a given date to be parsed is in the wrong format.
 */
public class InvalidDateException extends InvalidDateAndTimeException {

    /**
     * Creates an instance of InvalidDateException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidDateException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidDateException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
