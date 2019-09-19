package seedu.duke;

/**
 * Represents the exception thrown when the description of the "todo", "deadline", "event"
 * command is empty.
 *
 * */
public class EmptyDescriptionException extends DukeException {

    /**
     * Constructor of the EmptyDescriptionException class.
     *
     * @param errorMessage the error message to be thrown
     */
    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }

}
