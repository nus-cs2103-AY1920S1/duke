package duke.exception;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    } // End method.

    @Override
    public String getMessage() {
        return super.getMessage();
    } // End method.
}
