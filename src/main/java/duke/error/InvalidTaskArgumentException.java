package duke.error;

public class InvalidTaskArgumentException extends DukeException {
    public InvalidTaskArgumentException(String message) {
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
