/**
 * Custom Exception Class for Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor.
     */
    public DukeException() {
        super();
    }

    /**
     * Constructor.
     * @param errorMessage Message of error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructor.
     * @param errorMessage Message of error.
     * @param err Root cause of error.
     */
    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
