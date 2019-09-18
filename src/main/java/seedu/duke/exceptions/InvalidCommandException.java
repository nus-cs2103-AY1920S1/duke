package seedu.duke.exceptions;


/**
 * Signals that there was an issue with the command supplied by the user. This means that the command
 * could not be identified.
 * {@code InvalidCommandException.message} will contain information on the exact cause of the exception
 * together with the original cause of the exception (if available).
 * The default constructor contains the default message for these types of exceptions.
 */
public class InvalidCommandException extends DukeException {

    public InvalidCommandException() {
        super("No such command found.");
    }

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
