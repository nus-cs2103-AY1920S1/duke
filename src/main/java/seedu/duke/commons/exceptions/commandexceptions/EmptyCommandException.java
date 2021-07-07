package seedu.duke.commons.exceptions.commandexceptions;

/**
 * Defines a EmptyCommandException object that extends the InvalidCommandException class.
 * Signals an empty command was received by the Duke program.
 */
public class EmptyCommandException extends InvalidCommandException {

    /**
     * Creates an instance of EmptyCommandException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public EmptyCommandException(String message) {
        super(message);
    }

    /**
     * Creates an instance of EmptyCommandException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public EmptyCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
