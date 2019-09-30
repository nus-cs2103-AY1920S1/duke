package duke.exception;

/**
 * Thrown to indicate that a Command is invalid.
 */
public class DukeInvalidCommandException extends DukeExecutionException {
    public DukeInvalidCommandException(final String message) {
        super(message);
    }
}
