package seedu.duke.exceptions;

/**
 * Signals that there was an issue with the arguments specified by the user. This means that the command
 * was likely to have been identified correctly however failed to execute due to arguments that were either
 * missing or invalid. {@code InvalidArgumentException.message} will contain information on the exact cause of the
 * exception together with the original cause of the exception (if available).
 */
public class InvalidArgumentException extends DukeException {

    public InvalidArgumentException() {
        super();
    }

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message,cause);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
