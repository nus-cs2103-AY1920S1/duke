package duke.exception;

/**
 * Exception class for Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor method for DukeException.
     * 
     * @param errorMessage Error message to be displayed for the error
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
