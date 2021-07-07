package seedu.duke.commons.exceptions.loadexceptions;

/**
 * Defines a InvalidTaskTypeException object that extends the LoadException class.
 * Signals that an invalid task type was read from the Storage file while loading.
 */
public class InvalidTaskTypeException extends LoadException {

    /**
     * Creates an instance of InvalidTaskTypeException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidTaskTypeException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidTaskTypeException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidTaskTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
