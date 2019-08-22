package seedu.duke;

public class DukeNoSuchCommandException extends DukeException {

    public DukeNoSuchCommandException() {
        super("No such command found.");
    }

}
