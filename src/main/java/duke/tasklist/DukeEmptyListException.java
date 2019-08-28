package duke.tasklist;

import duke.DukeException;

/**
 * Exception to be thrown when an error occurs because the task list is empty
 */
public class DukeEmptyListException extends DukeException {

    /**
     * Constructs the exception to be thrown
     */
    public DukeEmptyListException() {
        super("Your list is empty!");
    }
}
