package seedu.duke.commons.exceptions.loadexceptions;

/**
 * Defines a InvalidTokenNumberException object that extends the LoadException class.
 * Signals that an invalid number of tokens was read from the Storage file while loading.
 */
public class InvalidTokenNumberException extends LoadException {

    /**
     * Creates an instance of InvalidTokenNumberException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidTokenNumberException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidTokenNumberException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidTokenNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
