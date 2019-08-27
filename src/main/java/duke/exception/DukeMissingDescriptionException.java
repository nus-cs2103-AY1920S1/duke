package duke.exception;

/**
 * Signals that some arguments are missing from the command input.
 */
public class DukeMissingDescriptionException extends DukeException {
    public DukeMissingDescriptionException(String description) {
        super(description);
    }
}
