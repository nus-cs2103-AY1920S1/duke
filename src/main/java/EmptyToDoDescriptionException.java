/**
 * This exception occurs when a ToDo description is empty.
 */

public class EmptyToDoDescriptionException extends DukeException {
    public EmptyToDoDescriptionException(String message) {
        super(message);
    }
}