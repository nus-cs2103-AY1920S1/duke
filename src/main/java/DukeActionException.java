/**
 * Represents a DukeActionException that inherits from DukeException.
 * Occurs when there are extra arguments to the action given to Duke.
 *
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class DukeActionException extends DukeException {

    /**
     * Creates a DukeActionException to be thrown.
     *
     * @param errorMessage Represents the Action that encountered this error.
     */
    public DukeActionException(String errorMessage) {
        super(errorMessage);
    }
}