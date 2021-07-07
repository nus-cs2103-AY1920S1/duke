package myduke.exception;

/**
 * An exception thrown upon processing an illegal command.
 */
public class DukeInvalidCommandException extends DukeException {
    public DukeInvalidCommandException(String message) {
        super(message);
    }
}
