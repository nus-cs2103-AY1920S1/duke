package duke.exception;

/**
 * Custom exception class.
 */
public class DukeException extends Exception {

    /**
     * Creates an instance of Duke Exception.
     */
    public DukeException() {
        super();
    }

    /**
     * Creates an instance of Duke Exception.
     *
     * @param errorMessage Message of error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Creates an instance of Duke Exception.
     *
     * @param errorMessage Message of error.
     * @param err Root cause of error.
     */
    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
