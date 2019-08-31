package duke.exception;

public class DukeException extends Exception {

    private String message;

    DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
