package duke.error;

public class InvalidTimeException extends DukeException {
    /**
     * Constructor.
     *
     * @param message String
     */
    public InvalidTimeException(String message) {
        super(message);
    }

    @Override
    /**
     * Overrides the toString method.
     *
     * @return String
     */
    public String toString() {
        return getMessage();
    }
}
