package jermi.exception;

/**
 * An exception associated with error in reading a data file.
 */
public class LoadingException extends JermiException {

    /**
     * Public constructor for class.
     *
     * @param message The cause of the error.
     */
    public LoadingException(String message) {
        super("An error has occurred while loading: " + message);
    }
}
