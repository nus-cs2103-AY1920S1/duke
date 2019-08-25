package duke.exception;

/**
 * Task Exception. Thrown when command does not start with "todo" / "deadline" / "event"
 */
public class TaskException extends DukeException {

    /**
     * Constructor.
     */
    public TaskException() {
        super();
    }

    /**
     * Constructor.
     * @param errorMessage Message of error.
     */
    public TaskException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructor.
     * @param errorMessage Message of error.
     * @param err Root cause of exception.
     */
    public TaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
