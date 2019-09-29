package duke.memo.exception;

public class DukeException extends Throwable {

    private String msg;

    public DukeException(String msg) {
        super();
        this.msg = msg;
    }

    public DukeException() {
        super();
    }

    public String getMessage() {
        return msg;
    }
}
