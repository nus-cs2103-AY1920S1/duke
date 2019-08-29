/**
 * Encapsulate the exception thrown when user command is not recognised.
 */
public class IllegalCommandException extends DukeException {

    /**
     * Constructs the IllegalCommandException object.
     *
     * @param msg Message of the exception.
     */
    public IllegalCommandException(String msg) {
        super(msg);
    }
}
