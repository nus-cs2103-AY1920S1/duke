package duke.exception;

/**
 * Custom Task Exception.
 */
public class TaskException extends DukeException {

    /**
     * Creates an instance of Task Exception.
     */
    public TaskException() {
        super();
    }

    /**
     * Creates an instance of Task Exception.
     *
     * @param errorMessage Message of error.
     */
    public TaskException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Creates an instance of Task Exception.
     *
     * @param errorMessage Message of error.
     * @param err Root cause of exception.
     */
    public TaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
