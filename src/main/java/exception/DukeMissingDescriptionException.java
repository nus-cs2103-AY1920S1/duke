package seedu.duke.exception;

public class DukeMissingDescriptionException extends DukeException {

    public DukeMissingDescriptionException() {
        super("Please add in the description as well.");
    }

}
