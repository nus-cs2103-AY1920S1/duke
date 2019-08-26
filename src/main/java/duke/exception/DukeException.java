package duke.exception;

public class DukeException extends IllegalArgumentException {

    String message;

    /**
     * Constructor to create a DukeException object.
     *
     * @param message
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Gets the error message.
     *
     * @return The error message of the exception.
     */
    @Override
    public String toString() {
        return message;
    }
}
