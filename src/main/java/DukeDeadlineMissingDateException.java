package seedu.duke;

public class DukeDeadlineMissingDateException extends DukeException {

    public DukeDeadlineMissingDateException() {
        super("For adding deadlines, please add the deadline date as well.");
    }

}
