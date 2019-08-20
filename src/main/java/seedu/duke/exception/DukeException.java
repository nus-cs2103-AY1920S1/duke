package seedu.duke.exception;

public class DukeException extends Exception{
    public DukeException() {
        super();
    }

    public DukeException(String msg) {
        super(msg);
    }

    public DukeException(String msg, Throwable cause) {
        super("OOPS!!! The description of a " + msg + " cannot be empty.", cause);
    }
}
