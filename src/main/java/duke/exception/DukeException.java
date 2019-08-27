package duke.exception;

public class DukeException extends Exception {
    private String errorMsg;

    public DukeException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String toString() {
        return errorMsg;
    }
}
