package duke.error;

public class InvalidTaskArgumentException extends DukeException {
    /**
     * Constructor.
     * 
     * @param message String
     */
    public InvalidTaskArgumentException(String message) {
        super(message);
    }
}
