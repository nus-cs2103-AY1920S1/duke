package exception;

/**
 * Exception due to Duke's user's invalid input.
 */
public class DukeException extends Exception {
    protected String message;

    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns a String representation of the Exception.
     *
     * @return String representation of the Exception.
     */
    @Override
    public String toString() {
        return message;
    }
}
