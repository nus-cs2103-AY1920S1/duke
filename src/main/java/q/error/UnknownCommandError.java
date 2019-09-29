package q.error;

/**
 * When there is an unknown Command.
 */
public class UnknownCommandError extends QException {
    /**
     * Constructs an UnknownCommandError.
     */
    public UnknownCommandError() {
        super("I don't know what that means.");
    }
}
