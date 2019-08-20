/**
 * Deadline Task Exception. Thrown when invalid deadline command.
 */
public class DeadlineTaskException extends TaskException {
    /**
     * Constructor.
     * @param errorMessage Message of the error.
     * @param err Root cause of error.
     */
    public DeadlineTaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
