/**
 * If user input is not in a valid format, then
 * DukeException will be thrown.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}