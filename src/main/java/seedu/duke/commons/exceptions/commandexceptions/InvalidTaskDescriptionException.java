package seedu.duke.commons.exceptions.commandexceptions;

/**
 * Defines a InvalidTaskDescriptionException object that extends the InvalidCommandException class.
 * Signals that a given task description is invalid (eg. empty for commands requiring task descriptions,
 * or non-empty for commands requiring empty task descriptions).
 */
public class InvalidTaskDescriptionException extends InvalidCommandException {

    /**
     * Creates an instance of InvalidTaskDescriptionException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidTaskDescriptionException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidTaskDescriptionException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidTaskDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
