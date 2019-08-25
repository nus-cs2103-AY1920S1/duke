package duke.exception;

/**
 * Custom ToDo Task Exception.
 */
public class ToDoTaskException extends TaskException {
    /**
     * Creates an instance of ToDo Task Exception.
     */
    public ToDoTaskException() {
        super();
    }

    /**
     * Creates an instance of ToDo Task Exception.
     *
     * @param errorMessage Message of error.
     */
    public ToDoTaskException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Creates an instance of ToDo Task Exception.
     *
     * @param errorMessage Message of error.
     * @param err Root cause of exception.
     */
    public ToDoTaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
