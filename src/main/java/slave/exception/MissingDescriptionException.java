package slave.exception;

/**
 * Missing Description Exception.
 */
public class MissingDescriptionException extends DukeException {

    /**
     * Constructor for MissingDescriptionException.
     */
    public MissingDescriptionException() {
        super("Task has no description!");
    }
}
