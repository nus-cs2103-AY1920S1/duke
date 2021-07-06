package duke.task;

/**
 * Represents an exceptional situation where the event is invalid.
 */
public class InvalidEventDukeException extends InvalidTaskDukeException {

    /**
     * Constructor for the exception.
     * @param message Message carried by the exception.
     */
    public InvalidEventDukeException(String message) {
        super(message);
    }

}
