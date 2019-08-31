package seedu.duke.exception;

public class DukeSaveFailedException extends DukeException {

    public DukeSaveFailedException() {
        super("Failed to save to file.");
    }

}
