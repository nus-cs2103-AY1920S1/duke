package jermi.exception;

/**
 * Base class for the checked exceptions in the Jermi program.
 */
public abstract class JermiException extends Exception {

    /**
     * Constructor for class
     *
     * @param message Error message.
     */
    JermiException(String message) {
        super("\u2639 OOPS! " + message);
    }
}
