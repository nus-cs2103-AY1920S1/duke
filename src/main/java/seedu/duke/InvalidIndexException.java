package seedu.duke;

/**
 * Represents the exception thrown when the index is invalid.
 */
public class InvalidIndexException extends DukeException {

    /**
     * Constructor of the InvalidIndexException class.
     *
     * @param errorMessage the error message to be thrown
     */
    public InvalidIndexException(String errorMessage) {
        super(errorMessage);
    }

}
