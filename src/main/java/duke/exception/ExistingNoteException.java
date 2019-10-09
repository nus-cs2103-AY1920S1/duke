package duke.exception;

/**
 * Thrown if the user attempts to add a Note with a title identical to an existing Note.
 */
public class ExistingNoteException extends DukeException {
    /**
     * Creates an ExistingNoteException exception.
     *
     * @param message Message to be printed.
     */
    public ExistingNoteException(String message) {
        super(message);
    }
}
