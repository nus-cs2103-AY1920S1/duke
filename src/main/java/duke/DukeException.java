package duke;

/**
 * General exception for Duke-related issues.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public DukeException(Throwable cause) {
        super(cause);
    }

    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}
