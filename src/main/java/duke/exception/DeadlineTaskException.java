package duke.exception;

/**
 * Custom Deadline Task Exception.
 */
public class DeadlineTaskException extends TaskException {
    /**
     * Creates an instance of Deadline Task Exception.
     */
    public DeadlineTaskException() {
        super();
    }

    /**
     * Creates an instance of Deadline Task Exception.
     *
     * @param errorMessage Message of error.
     */
    public DeadlineTaskException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Creates an instance of Deadline Task Exception.
     *
     * @param errorMessage Message of the error.
     * @param err Root cause of error.
     */
    public DeadlineTaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
