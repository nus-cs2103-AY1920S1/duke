package duke.exception;

/**
 * Custom Event Task Exception.
 */
public class EventTaskException extends TaskException {
    /**
     * Creates an instance of Event Task Exception.
     */
    public EventTaskException() {
        super();
    }

    /**
     * Creates an instance of Event Task Exception.
     *
     * @param errorMessage Message of error.
     */
    public EventTaskException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Creates an instance of Event Task Exception.
     *
     * @param errorMessage Message of error.
     * @param err Root cause of error.
     */
    public EventTaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
