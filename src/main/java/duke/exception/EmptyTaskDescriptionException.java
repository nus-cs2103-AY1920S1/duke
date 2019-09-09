package duke.exception;

/**
 * Thrown if the user attempts to add a Task with an empty description.
 */
public class EmptyTaskDescriptionException extends DukeException {
    /**
     * Creates an EmptyTaskDescriptionException exception.
     *
     * @param message Message to be printed.
     */
    public EmptyTaskDescriptionException(String message) {
        super(message);
    }
}
