/**
 * An exception that occurs when a task has no description.
 */

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
