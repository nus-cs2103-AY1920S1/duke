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

    /**
     * Returns a DukeException object with an error message and original cause.
     *
     * @param errorMessage error message to be associated with exception
     * @param cause the original cause of exception (eg. an internally thrown exception)
     */
    public DukeException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
