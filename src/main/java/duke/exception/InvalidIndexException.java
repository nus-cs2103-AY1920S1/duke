package duke.exception;

;

public class InvalidIndexException extends DukeException {
    /**
     * Constructor for <code>InvalidIndexException</code>.
     */
    public InvalidIndexException() {
        super("OOPS!!! This item does not exist.");
    }
}
