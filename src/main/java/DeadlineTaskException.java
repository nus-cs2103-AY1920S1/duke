/**
 * Deadline Task Exception. Thrown when invalid deadline command.
 */
public class DeadlineTaskException extends TaskException {
    /**
     * Constructor.
     */
    public DeadlineTaskException() {
        super();
    }

    /**
     * Constructor.
     * @param errorMessage Message of error.
     */
    public DeadlineTaskException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructor.
     * @param errorMessage Message of the error.
     * @param err Root cause of error.
     */
    public DeadlineTaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
