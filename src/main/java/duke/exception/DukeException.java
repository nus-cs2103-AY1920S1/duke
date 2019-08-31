package duke.exception;

public class DukeException extends Exception {
    String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    public String getMessage() {
        return message;
    }
}
