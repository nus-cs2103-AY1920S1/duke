/**
 * ToDo Task Exception. Thrown when invalid ToDo command.
 */
public class ToDoTaskException extends TaskException {
    /**
     * Constructor.
     * @param errorMessage Message of error.
     * @param err Root cause of exception.
     */
    public ToDoTaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
