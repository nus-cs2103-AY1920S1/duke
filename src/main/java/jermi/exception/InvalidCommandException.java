package jermi.exception;

/**
 * An exception associated with invalid command given.
 */
public class InvalidCommandException extends JermiException {

    /**
     * Public constructor for class.
     */
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
