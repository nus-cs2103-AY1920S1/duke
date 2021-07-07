/**
 * Signals an error caused by any data or input error in Duke program.
 */
public class DukeException extends Exception {

    private String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
