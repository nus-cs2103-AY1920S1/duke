package exception;

public class DukeInvalidTaskDateFormatException extends DukeException {

    public DukeInvalidTaskDateFormatException(String msg) {
        super(msg + " is not a valid date.");
    }

}
