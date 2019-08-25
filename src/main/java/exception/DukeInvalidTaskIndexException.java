package exception;

public class DukeInvalidTaskIndexException extends DukeException {

    public DukeInvalidTaskIndexException (String msg, int n) {
        super("Invalid numbered task to " + msg + ". Insert a number from 1 to " + n + ".");
    }

}
