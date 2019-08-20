package seedu.duke;

public class DukeException extends Exception{
    public DukeException() {
        super();
    }

    public DukeException(String msg) {
        super("OOPS!!! The description/time of a " + msg + " cannot be empty.");
    }

    public DukeException(String msg, Throwable cause) {
        super("OOPS!!! The description of a " + msg + " cannot be empty.", cause);
    }
}
