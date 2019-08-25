package duke;

public class DukeInvalidArgumentException extends DukeExceptions {
    public DukeInvalidArgumentException(String displayMsg) {
        super("Invalid user arguments to command inputted", displayMsg);
    }

    public DukeInvalidArgumentException(String errorMsg, String displayMsg) {
        super(errorMsg, displayMsg);
    }
}
