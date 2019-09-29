package duke.memo.exception;

public class NotNumberException extends DukeException {

    public NotNumberException() {
        super();
    }

    @Override
    public String getMessage() {
        return "OOPS!!! there is no number entered";
    }
}
