package duke.exception;

/**
 * Thrown to indicate that Duke has failed to execute a Command.
 */
public class DukeExecutionException extends DukeException {
    public DukeExecutionException(final String message) {
        super(message);
    }
}
