package duke.exception;

/**
 * Thrown if the user enters an unsupported command name.
 */
public class InvalidInstructionException extends DukeException {
    /**
     * Creates an InvalidInstructionException exception.
     *
     * @param message Message to be printed.
     */
    public InvalidInstructionException(String message) {
        super(message);
    }
}
