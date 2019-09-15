package duke.exception;

/**
 * This exception is thrown when a user wants to perform an action
 * on a task that does not exist in the task list.
 */
public class TaskDoesNotExistException extends DukeException {
    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
