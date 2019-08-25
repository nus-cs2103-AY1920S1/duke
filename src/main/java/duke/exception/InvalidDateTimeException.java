package duke.exception;

/**
 * Custom Invalid DateTime Exception.
 */
public class InvalidDateTimeException extends Exception {
    /**
     * Creates an instance of Invalid DateTime Exception.
     */
    public InvalidDateTimeException() {
        super();
    }

    /**
     * Creates an instance of Invalid DateTime Exception.
     *
     * @param errorMessage Message of error.
     */
    public InvalidDateTimeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Creates an instance of Invalid DateTime Exception.
     *
     * @param errorMessage Message of error.
     * @param err Root cause of error.
     */
    public InvalidDateTimeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
