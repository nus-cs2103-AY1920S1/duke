package duke.exception;

/**
 * Inherits from abstract DukeException class.
 * Handles errors related to invalid commands
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
