/**
 * Class representation of the Exception that occurs when an empty description is provided to a Task.
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Creates an EmptyDescriptionException with a specific message.
     * 
     * @param message Exception message
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}