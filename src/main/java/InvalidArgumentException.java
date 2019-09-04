/**
 * Represents an exception thrown when arguments are invalid.
 */
public class InvalidArgumentException extends DukeException {

    /**
     * Creates a <code>InvalidArgumentException</code> for the
     * given error message.
     *
     * @param message Error message.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}