/**
 * Event Task Exception. Thrown when invalid event command.
 */
public class EventTaskException extends TaskException {
    /**
     * Constructor.
     * @param errorMessage Message of error.
     * @param err Root cause of error.
     */
    public EventTaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
