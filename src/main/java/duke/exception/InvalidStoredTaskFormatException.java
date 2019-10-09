package duke.exception;

/**
 * Thrown if the user attempts to add a Task with an empty description.
 */
public class InvalidStoredTaskFormatException extends DukeException {
    /**
     * Creates an EmptyTaskDescriptionException exception.
     *
     * @param message Message to be printed.
     */
    public InvalidStoredTaskFormatException(String message) {
        super(message);
    }
}
