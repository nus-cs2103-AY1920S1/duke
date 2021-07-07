/**
 * Represents a DukeEventException that inherits from DukeException.
 * Occurs when the duration of the task is not formatted properly or missing.
 *
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class DukeEventException extends DukeException {

    /**
     * Creates a DukeEventException to be thrown.
     * @param errorMessage Represents the event task that encountered this error.
     */
    public DukeEventException(String errorMessage) {
        super(errorMessage);
    }
}
