package jermi.exception;

/**
 * An exception associated with error in writing contents to a data file.
 */
public class SavingException extends JermiException {

    /**
     * Public constructor for class.
     *
     * @param message The cause of the error.
     */
    public SavingException(String message) {
        super("An error has occurred while saving: " + message);
    }
}
