package exceptions;

/**
 * The InvalidDescriptionException class handles all invalid description
 * added after the user input the command.
 */
public class  InvalidDescriptionException extends DukeException {
    /**
     * Constructs and initializes the attributes of a new InvalidDescriptionException
     * object.
     * @param message The message to be shown by the exception.
     */
    public InvalidDescriptionException(String message) {
        super(message);
    }
}

