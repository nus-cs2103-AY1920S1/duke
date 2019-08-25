package duke.exception;

/**
 * ToDo Task Exception. Thrown when invalid ToDo command.
 */
public class ToDoTaskException extends TaskException {
    /**
     * Constructor.
     */
    public ToDoTaskException() {
        super();
    }

    /**
     * Constructor.
     * @param errorMessage Message of error.
     */
    public ToDoTaskException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructor.
     * @param errorMessage Message of error.
     * @param err Root cause of exception.
     */
    public ToDoTaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
