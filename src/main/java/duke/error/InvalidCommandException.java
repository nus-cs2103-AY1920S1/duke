package duke.error;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
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
