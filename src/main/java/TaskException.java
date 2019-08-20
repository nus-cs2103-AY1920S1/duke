/**
 * Task Exception. Thrown when command does not start with "todo" / "deadline" / "event"
 */
public class TaskException extends DukeException {
    /**
     * Constructor.
     * @param errorMessage Message of error.
     * @param err Root cause of exception.
     */
    public TaskException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
