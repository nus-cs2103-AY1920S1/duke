package duke.error;

public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String message) {
        super(message);
    }

    @Override
    /**
     * Overrides the toString method.
     * @return String
     */
    public String toString() {
        return getMessage();
    }
}
