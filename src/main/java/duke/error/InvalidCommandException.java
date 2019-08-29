package duke.error;

public class InvalidCommandException extends DukeException {
    /**
     * Constructor.
     *
     * @param message String
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
