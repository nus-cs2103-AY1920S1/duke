public class DukeInvalidTaskDateException extends DukeException {

    public DukeInvalidTaskDateException(String msg) {
        super(msg + " is not a valid date.");
    }

}
