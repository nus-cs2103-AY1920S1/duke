package duke.error;

public class InvalidFileException extends DukeException {
    public InvalidFileException(String message) {
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
