package duke.command.exception;

import duke.exception.DukeException;

/**
 * Encapsulate the exception thrown when task description is not found.
 */
public class MissingDescriptionException extends DukeException {

    /**
     * Constructs the TaskNotFoundException object.
     *
     * @param msg Message of the exception.
     */
    public MissingDescriptionException(String msg) {
        super(msg);
    }

}
