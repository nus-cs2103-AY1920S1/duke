package duke.exception;

/**
 * Thrown when Duke fails to interact with disk storage.
 */
public class DukeStorageException extends DukeException {
    public DukeStorageException(final String message) {
        super(message);
    }
}
