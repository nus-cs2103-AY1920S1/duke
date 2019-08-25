package jermi.exception;

/**
 * An exception associated with empty description.
 */
public class EmptyDescriptionException extends JermiException {

    /**
     * Public constructor for class.
     *
     * @param message The cause of the error.
     */
    public EmptyDescriptionException(String message) {
        super(String.format("The description of %s cannot be empty.", message));
    }
}
