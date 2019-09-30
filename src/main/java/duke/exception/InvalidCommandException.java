package duke.exception;

/**
 * The class handles errors to do with invalid commands.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Public constructor for InvalidCommandException.
     *
     * @param message The error message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
