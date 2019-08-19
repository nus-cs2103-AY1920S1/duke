package duke.error;

public class InvalidDoneCommandException extends DukeException {
    public InvalidDoneCommandException(String message) {
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
