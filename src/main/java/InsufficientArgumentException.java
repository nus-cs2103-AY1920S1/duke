/**
 * Represents an exception thrown when arguments are insufficient.
 */
public class InsufficientArgumentException extends DukeException{

    /**
     * Creates a <code>InsufficientArgumentException</code> for the
     * given error message.
     *
     * @param message Error message.
     */
    public InsufficientArgumentException(String message) {
        super(message);
    }
}