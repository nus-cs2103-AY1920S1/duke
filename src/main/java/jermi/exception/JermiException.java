package jermi.exception;

import static jermi.misc.Constant.EXCEPTION_ERROR_MESSAGE;

/**
 * Base class for the checked exceptions in the Jermi program.
 */
public abstract class JermiException extends Exception {
    /**
     * Constructor for class.
     *
     * @param message Error message.
     */
    JermiException(String message) {
        super(String.format(EXCEPTION_ERROR_MESSAGE, message));
    }
}
