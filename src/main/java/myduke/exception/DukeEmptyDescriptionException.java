package myduke.exception;

/**
 * An exception thrown when a non-optional field is empty.
 */
public class DukeEmptyDescriptionException extends DukeException {
    public DukeEmptyDescriptionException(String message) {
        super(message);
    }
}
