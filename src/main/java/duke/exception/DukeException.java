package duke.exception;

public class DukeException extends Exception {
    protected String message;

    public DukeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
