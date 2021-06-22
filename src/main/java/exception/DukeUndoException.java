package exception;

/**
 * Responsible for problems occured during parsing a user input.
 * Mainly used for DateTime format parsing errors.
 */
public class DukeUndoException extends DukeException {
    public DukeUndoException() {
        super("Undo failed, there is no last action.");
    }
}