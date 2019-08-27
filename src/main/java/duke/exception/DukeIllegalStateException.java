package duke.exception;

/**
 * Signals that Duke is not in an appropriate state for the request operation.
 */
public class DukeIllegalStateException extends DukeException {
    public DukeIllegalStateException(String description) {
        super(description);
    }
}
