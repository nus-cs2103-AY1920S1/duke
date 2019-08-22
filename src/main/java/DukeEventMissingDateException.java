package seedu.duke;

public class DukeEventMissingDateException extends DukeException {

    public DukeEventMissingDateException() {
        super("For adding event, please add the event start date as well.");
    }

}
