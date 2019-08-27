package duke.exception;

/**
 * Represents a checked <code>Exception</code>.
 */
public class DukeException extends Exception {
    /**
     * Constructor for <code>DukeException</code>.
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
