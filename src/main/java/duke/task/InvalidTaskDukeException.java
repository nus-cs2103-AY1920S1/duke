package duke.task;

import duke.exception.DukeException;

/**
 * Represents an exceptional situation where the task is invalid.
 */
public class InvalidTaskDukeException extends DukeException {

    /**
     * Constructor for an invalid task exception.
     * @param message Message carried by the exception.
     */
    public InvalidTaskDukeException(String message) {
        super(message);
    }

}
