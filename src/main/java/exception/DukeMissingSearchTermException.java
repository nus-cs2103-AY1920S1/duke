package seedu.duke.exception;

public class DukeMissingSearchTermException extends DukeException {

    public DukeMissingSearchTermException() {
        super("Please add in the search term.");
    }

}
