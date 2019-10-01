package duke.exception;

/**
 * Represents a List exception that occurs during Duke's execution.
 */
public class DukeListException extends DukeException {

    /**
     * Constructs a new DukeListException with the specified error message.
     *
     * @param errorMsg the error message as a String.
     */
    public DukeListException(String errorMsg) {
        super(errorMsg);
    }
}
