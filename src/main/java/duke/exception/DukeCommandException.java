package duke.exception;

/**
 * Represents a Command exception that occurs during Duke's execution.
 */
public class DukeCommandException extends DukeException {

    /**
     * Constructs a new DukeCommandException with the specified error message.
     *
     * @param errorMsg the error message as a String.
     */
    public DukeCommandException(String errorMsg) {
        super(errorMsg);
    }
}
