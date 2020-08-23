package duke.error;

public class InvalidIndexException extends DukeException {
    /**
     * Constructor.
     *
     * @param message String
     */
    public InvalidIndexException(String message) {
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
