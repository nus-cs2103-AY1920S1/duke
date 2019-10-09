package duke.exception;

/**
 * Thrown if the user attempts to view the current task list if the list is empty.
 */
public class EmptyTaskListException extends DukeException {
    /**
     * Creates an EmptyTaskListException exception.
     *
     * @param message Message to be printed.
     */
    public EmptyTaskListException(String message) {
        super(message);
    }
}
