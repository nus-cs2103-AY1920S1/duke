package duke.util;

/**
 * Represent an Exception thrown by the application.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("\u2639 %s", super.getMessage());
    }
}
