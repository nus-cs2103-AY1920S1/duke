package seedu.duke.exceptions;

public class EmptyEventArgException extends DukeException {

    public EmptyEventArgException() {
        super("The time of an event cannot be empty, e.g. event play tennis /at 12/06/2021 2000");
    }
}
