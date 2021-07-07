package seedu.duke.commons.exceptions.loadexceptions;

/**
 * Defines a InvalidListIndexException object that extends the LoadException class.
 * Signals that a done status read from the Storage file while loading is invalid (i.e. neither 0 nor 1).
 */
public class InvalidDoneStatusException extends LoadException {

    /**
     * Creates an instance of InvalidListIndexException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidDoneStatusException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidListIndexException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidDoneStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
