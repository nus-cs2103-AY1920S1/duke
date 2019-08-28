package myduke.exception;

/**
 * An exception thrown upon receiving an illegal argument.
 */
public class DukeIllegalArgumentException extends DukeException {
    public DukeIllegalArgumentException(String message) {
        super(message);
    }
}
