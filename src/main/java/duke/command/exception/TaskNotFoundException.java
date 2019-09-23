package duke.command.exception;

import duke.exception.DukeException;

/**
 * Encapsulate the exception thrown when user's query index for task exceeds the number of tasks on the list.
 */
public class TaskNotFoundException extends DukeException {

    /**
     * Constructs the TaskNotFoundException object.
     *
     * @param msg Message of the exception.
     */
    public TaskNotFoundException(String msg) {
        super(msg);
    }

}
