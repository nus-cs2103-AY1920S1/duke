package duke.exception;

/**
 * Exception for this program.
 */
public class DukeException extends Exception {
    /**
     * Creates an exception with the specified message.
     * @param message a String that is used as the error message
     */
    public DukeException(String message) {
        super(message);
    }
}
