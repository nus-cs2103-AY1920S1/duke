package seedu.duke;

/**
 * Represents the exception thrown when the find description is invalid.
 */
public class InvalidFindDescription extends DukeException {

    /**
     * Constructor of the InvalidFindDescription class.
     *
     * @param errorMessage the error message to be thrown
     */
    public InvalidFindDescription(String errorMessage) {
        super(errorMessage);
    }

}
