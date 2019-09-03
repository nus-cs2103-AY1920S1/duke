package duke.exception;

@SuppressWarnings("serial")
public class DukeException extends Exception {
    // Stores the command whose parsing led to the exception being thrown
    protected String errorSource;

    /**
     *  Constructs a generic <code>DukeException</code> object with an error message and the error source.
     *  @param message <code>String</code> message to be displayed to the user.
     *  @param errorSource <code>String</code> containing information of the error source; typically a user command.
     */
    public DukeException(String message, String errorSource) {
        super(message);
        this.errorSource = errorSource;
    }

    @Override
    public String toString() {
        return String.format("[DukeException] %s\n  > %s", this.getMessage(), this.errorSource); 
    }
}
