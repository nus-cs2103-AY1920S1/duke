package seedu.duke;

public class DukeSaveFailedException extends DukeException {

    public DukeSaveFailedException() {
        super("Failed to save to file.");
    }

}
