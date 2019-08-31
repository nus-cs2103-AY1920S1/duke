package cs2103t.duke.exception;

/**
 * Represents exceptions where done or delete command is given an invalid id.
 */
public class InvalidIDException extends DukeException {
    /** Position of task in list. */
    private String id;

    /**
     * Constructs an InvalidIDException.
     * @param msg position of task in list (1-based).
     */
    public InvalidIDException(String msg) {
        super(msg);
        this.id = msg;
    }

    @Override
    public String toString() {
        return String.format("\u2639 AIGOO!! %s is an invalid ID!", this.id);
    }
}
