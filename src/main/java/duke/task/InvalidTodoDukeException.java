package duke.task;

/**
 * Represents an exceptional situation where the to-do is invalid.
 */
public class InvalidTodoDukeException extends InvalidTaskDukeException {

    /**
     * Constructor for the to-do exception.
     * @param message Message carried by the exception.
     */
    public InvalidTodoDukeException(String message) {
        super(message);
    }

}
