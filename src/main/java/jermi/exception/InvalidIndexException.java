package jermi.exception;

/**
 * An exception associated with invalid index given.
 */
public class InvalidIndexException extends JermiException {

    /**
     * Public constructor for class.
     */
    public InvalidIndexException() {
        super("The task index is invalid!");
    }
}
