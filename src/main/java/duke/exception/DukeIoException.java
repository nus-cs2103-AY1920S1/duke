package duke.exception;

/**
 * Signals that some I/O error occurred when reading/writing from some file.
 */
public class DukeIoException extends DukeException {
    public DukeIoException(String description) {
        super(description);
    }
}
