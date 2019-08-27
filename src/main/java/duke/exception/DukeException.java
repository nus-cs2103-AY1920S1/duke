package duke.exception;

/**
 * Represents a generic exception that occurs in the Duke App
 */
public class DukeException extends Exception {
    public DukeException(String eventType) {
        super(eventType);
    }
}
