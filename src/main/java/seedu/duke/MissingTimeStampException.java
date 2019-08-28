package seedu.duke;

/**
 * Represents the exception thrown when the timestamp of the deadline or event
 * command is empty.
 */
public class MissingTimeStampException extends DukeException {

    /**
     * Constructor of the MissingTimeStampException.
     *
     * @param errorMessage
     */
    public MissingTimeStampException(String errorMessage) {
        super(errorMessage);
    }

}
