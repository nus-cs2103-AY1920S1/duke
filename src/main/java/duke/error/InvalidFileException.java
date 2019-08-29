package duke.error;

public class InvalidFileException extends DukeException {
    /**
     * Constructor.
     *
     * @param message String
     */
    public InvalidFileException(String message) {
        super(message);
    }
}
