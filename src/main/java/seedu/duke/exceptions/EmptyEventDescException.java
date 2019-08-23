package seedu.duke.exceptions;

public class EmptyEventDescException extends DukeException {

    public EmptyEventDescException() {
        super(" OOPS!!! The description of an event cannot be empty.");
    }
}
