package duke.exception;

/**
 * Represents an exception when Task has incorrect input. Used when Deadline or Event has incorrect DateTime format.
 */
public class InvalidTaskDukeException extends DukeException {
    public InvalidTaskDukeException(String message) {
        super(message);
    }
}
