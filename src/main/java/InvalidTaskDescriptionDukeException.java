
/**
 * Represents an exception that is thrown when a task object is created with an invalid description argument.
 */
public class InvalidTaskDescriptionDukeException extends InvalidTaskArgumentDukeException {
    public InvalidTaskDescriptionDukeException(String message) {
        super(message);
    }
}
