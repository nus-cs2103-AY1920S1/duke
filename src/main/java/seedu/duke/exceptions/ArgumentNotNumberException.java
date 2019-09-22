package seedu.duke.exceptions;

public class ArgumentNotNumberException extends DukeException {

    public ArgumentNotNumberException() {
        super("Argument must be a number.");
    }
}
