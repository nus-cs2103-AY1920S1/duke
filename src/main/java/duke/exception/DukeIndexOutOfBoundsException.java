package duke.exception;

/**
 * Signals that the program tried to access some collection out of its index.
 */
public class DukeIndexOutOfBoundsException extends DukeException {
    public DukeIndexOutOfBoundsException(String description) {
        super(description);
    }
}
