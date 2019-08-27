package duke.exception;

/**
 * Signals that some I/O exception of some sort has occurred.
 */
public class DukeIOException extends DukeException {
    public DukeIOException(String description) {
        super(description);
    }
}
