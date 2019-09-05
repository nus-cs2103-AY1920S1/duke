package duke.task;

/**
 * Represents an exceptional situation where the the deadline is invalid.
 */
public class InvalidDeadlineDukeException extends InvalidTaskDukeException {

    /**
     * Constructor for the deadline exception.
     * @param message The message carried by the exception.
     */
    public InvalidDeadlineDukeException(String message) {
        super(message);
    }

}
