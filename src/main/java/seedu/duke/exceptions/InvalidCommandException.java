package seedu.duke.exceptions;

public class InvalidCommandException extends DukeException {

    @Override
    public String toString() {
        return "No such command found.";
    }
}
