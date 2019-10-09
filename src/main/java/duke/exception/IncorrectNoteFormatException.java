package duke.exception;

/**
 * Thrown if the user attempts to add a Note with an incorrect Note format.
 */
public class IncorrectNoteFormatException extends DukeException {
    /**
     * Creates an IncorrectNoteFormatException exception.
     *
     * @param message Message to be printed.
     */
    public IncorrectNoteFormatException(String message) {
        super(message);
    }
}
