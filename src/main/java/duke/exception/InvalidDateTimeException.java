package duke.exception;

/**
 * Invalid DateTime Exception. Thrown if date time format is wrong.
 */
public class InvalidDateTimeException extends Exception {
    /**
     * Constructor.
     */
    public InvalidDateTimeException() {
        super();
    }

    /**
     * Constructor.
     * @param errorMessage Message of error.
     */
    public InvalidDateTimeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructor.
     * @param errorMessage Message of error.
     * @param err Root cause of error.
     */
    public InvalidDateTimeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
