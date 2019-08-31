package seedu.duke;

/**
 * Represents the exception thrown when the user input is invalid.
 */
public class InvalidInputException extends DukeException {

    /**
     * Constructor of InvalidInputException class.
     *
     * @param errorMessage the error message to be thrown
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }

}
