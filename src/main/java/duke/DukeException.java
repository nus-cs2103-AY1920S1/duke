package duke;

/**
 * Generic exception used by Duke application.
 */
public class DukeException extends Exception {
    /**
     * Creates a exception with a message.
     *
     * @param message description of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
