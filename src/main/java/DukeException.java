/**
 * Represents a DukeException that inherits from Exception.
 *
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException to be thrown.
     * @param errorMessage Represents the error to be displayed.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}