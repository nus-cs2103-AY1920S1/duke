package duke.exception;

/**
 * Thrown if the user enters an unsupported command name.
 */
public class InvalidNoteInstructionException extends DukeException {
    /**
     * Creates an InvalidInstructionException exception.
     *
     * @param message Message to be printed.
     */
    public InvalidNoteInstructionException(String message) {
        super(message);
    }
}
