/**
 * Represents a DukeEmptyException that inherits from DukeException.
 * Occurs when the description of the task is missing.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class DukeEmptyException extends DukeException {

    /**
     * Creates a DukeEmptyException to be thrown.
     * @param errorMessage Represents the task that encountered this error.
     */
    public DukeEmptyException(String errorMessage) {
        super("The description of a " + errorMessage + " cannot be empty.");
    }
}
