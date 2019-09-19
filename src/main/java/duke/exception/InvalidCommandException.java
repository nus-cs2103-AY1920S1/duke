package duke.exception;

public class InvalidCommandException extends DukeException {
    /**
     * Constructs an invalid command exception.
     *
     * @param message Description of the exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
