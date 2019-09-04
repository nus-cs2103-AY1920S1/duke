package duke.exception;

/**
 * A checked exception thrown during the execution of the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param errorMsg the error message as a String.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
