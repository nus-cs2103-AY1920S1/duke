package duke.exception;

/**
 * The general exception type thrown by Duke.
 */
public class DukeException extends RuntimeException {
    public DukeException(String description) {
        super(description);
    }
}
