package seedu.duke;

public class DukeWrongDateFormatException extends DukeException {
    public DukeWrongDateFormatException() {
        super("The date is in wrong format. Please enter in the following format: dd/MM/yyyy HHmm");
    }
}

