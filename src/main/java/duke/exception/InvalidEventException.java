package duke.exception;

public class InvalidEventException extends DukeException {

    public String message;

    public InvalidEventException(String message) {
        this.message = message;
    }

    public String toString() {
        return "OOPS!!! " + message;
    }

}