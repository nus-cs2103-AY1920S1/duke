package slave.exception;

/**
 * Missing Description Exception.
 */
public class MissingDescriptionException extends KappaException {

    /**
     * Constructor for MissingDescriptionException.
     */
    public MissingDescriptionException() {
        super("Task has no description!");
    }
}
