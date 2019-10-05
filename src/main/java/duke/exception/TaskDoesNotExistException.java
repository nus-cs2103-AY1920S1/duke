package duke.exception;

/**
 * Inherits from abstract DukeException class.
 * Handles error when user tries to perform an operation on a task that does not exist in taskList
 */
public class TaskDoesNotExistException extends DukeException {

    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
