package duke.exception;

/**
 * Exception class for Duke application.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException object with the given message.
     *
     * @param message Message of exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
