package duke.exception;

/**
 * Thrown if the user attempts to load or delete a Note from a non-existent file path.
 */
public class NoSuchNoteException extends DukeException {
    /**
     * Creates a NoSuchNoteException exception.
     *
     * @param message Message to be printed.
     */
    public NoSuchNoteException(String message) {
        super(message);
    }
}
