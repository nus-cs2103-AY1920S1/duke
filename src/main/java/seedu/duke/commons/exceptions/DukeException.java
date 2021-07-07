package seedu.duke.commons.exceptions;

/**
 * Defines a DukeException object that extends the Exception class.
 * Signals an exception occurred that is relevant for this Duke program.
 */
public class DukeException extends Exception {

    /**
     * Creates an instance of DukeException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Creates an instance of DukeException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}
