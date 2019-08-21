/**
 * Event Task Exception. Thrown when invalid event command.
 */
public class EventTaskException extends TaskException {
    /**
     * Constructor.
     */
    public EventTaskException() {
        super();
    }

    /**
     * Constructor.
     * @param errorMessage Message of error.
     */
    public EventTaskException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructor.
     * @param errorMessage Message of error.
     * @param err Root cause of error.
     */
    public EventTaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
