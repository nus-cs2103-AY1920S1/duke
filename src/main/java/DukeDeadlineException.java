/**
 * Represents a DukeDeadlineException that inherits from DukeException.
 * Occurs when the deadline of the task is not formatted properly or missing.
 *
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class DukeDeadlineException extends DukeException {

    /**
     * Creates a DukeDeadlineException to be thrown.
     * @param errorMessage Represents the deadline task that encountered this error.
     */
    public DukeDeadlineException(String errorMessage) {
        super(errorMessage);
    }
}
