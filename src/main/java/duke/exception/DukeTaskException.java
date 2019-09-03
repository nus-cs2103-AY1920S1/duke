package duke.exception;

/**
 * Represents a Task exception that occurs during duke's execution.
 */
public class DukeTaskException extends DukeException {

    /**
     * Constructs a new DukeTaskException with the specified error message.
     *
     * @param errorMsg the error message as a String.
     */
    public DukeTaskException(String errorMsg) {
        super(errorMsg);
    }
}

