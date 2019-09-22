package seedu.duke.exceptions;

public class EmptyDeadlineDescException extends DukeException {

    public EmptyDeadlineDescException() {
        super("The description of a deadline cannot be empty.");
    }
}
