package duke.exception;

/**
 * Generic exception thrown to indicate an unusual application state that should be caught.
 */
@SuppressWarnings("serial")
public class DukeException extends Exception {
    protected String errorSource;

    /**
     * Constructs a generic <code>DukeException</code> object with an error message and the error source.
     * 
     * @param message <code>String</code> message to be displayed to the user.
     * @param errorSource <code>String</code> containing information of the error source; typically a user command.
     */
    public DukeException(String message, String errorSource) {
        super(message);
        this.errorSource = errorSource;
    }

    @Override
    public String toString() {
        return String.format("[DukeException]\n%s\n  > %s", this.getMessage(), this.errorSource); 
    }
}
