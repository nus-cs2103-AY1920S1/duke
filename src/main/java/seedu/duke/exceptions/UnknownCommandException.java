package seedu.duke.exceptions;

public class UnknownCommandException extends DukeException {

    public UnknownCommandException() {
        super("What are you even saying?");
    }
}
