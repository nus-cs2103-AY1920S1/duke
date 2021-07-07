package seedu.duke.commons.exceptions.listexceptions;

/**
 * Defines a InvalidListRangeException object that extends the InvalidListIndexException class.
 * Signals that a given list range is in the wrong format, or given list indexes exceeds the size of the list.
 */
public class InvalidListRangeException extends InvalidListIndexException {

    /**
     * Creates an instance of InvalidListRangeException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidListRangeException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidListRangeException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidListRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
