package duke.exception;

/** Thrown when user inputs an invalid command. */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
