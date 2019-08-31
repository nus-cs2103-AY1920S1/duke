package cs2103t.duke.exception;

/**
 * Represents exceptions where an invalid keyword (of command) is given.
 */
public class InvalidKeywordException extends DukeException {
    /**
     * Constructs an InvalidKeywordException.
     * @param msg error message.
     */
    public InvalidKeywordException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
