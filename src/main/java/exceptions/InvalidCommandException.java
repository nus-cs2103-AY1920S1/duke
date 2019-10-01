package exceptions;

/**
 * The InvalidCommandException class handles all invalid command
 * input by the user.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs and initializes the attributes of a new InvalidCommandException
     * object.
     * @param message The message to be shown by the exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}

