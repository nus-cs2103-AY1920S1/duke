package duke.exception;

/**
 * Thrown if the user attempts to refer to a task item integer not in the list.
 */
public class InvalidIntegerTaskListException extends DukeException {
    /**
     * Creates an InvalidIntegerTaskListException exception.
     *
     * @param message Message to be printed.
     */
    public InvalidIntegerTaskListException(String message) {
        super(message);
    }
}
