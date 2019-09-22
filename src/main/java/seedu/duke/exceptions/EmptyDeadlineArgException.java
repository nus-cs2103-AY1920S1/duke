package seedu.duke.exceptions;

public class EmptyDeadlineArgException extends DukeException {

    public EmptyDeadlineArgException() {
        super("The time of an deadline cannot be empty, e.g. deadline do homework /by 12/12/2010 1230");
    }

}
