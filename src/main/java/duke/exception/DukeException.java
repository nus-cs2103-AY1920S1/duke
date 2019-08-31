package duke.exception;

/**
 * Exception class specific to Duke that represents
 * recoverable errors encountered during Duke execution.
 */
public class DukeException extends Exception {

    /**
     * Returns a DukeException object with an associated error message.
     * @param errorMessage error message to be associated with exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
