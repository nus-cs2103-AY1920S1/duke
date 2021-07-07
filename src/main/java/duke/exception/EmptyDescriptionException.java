package duke.exception;

/** Thrown when user inputs a task but leaves the description field empty. */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
