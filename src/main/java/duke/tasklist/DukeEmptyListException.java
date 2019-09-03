package duke.tasklist;

import duke.error.DukeException;

/**
 * Exception to be thrown when an error occurs because the TaskList is empty.
 */
public class DukeEmptyListException extends DukeException {

    /**
     * Constructs the DukeException to be thrown when an error occurs because the TaskList is empty.
     */
    public DukeEmptyListException() {
        super("Your list is empty!");
    }
}
