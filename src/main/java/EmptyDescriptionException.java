/**
 * Class representation of the Exception that occurs when an empty description is provided to a Task.
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * 
     * @param message Exception message
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}