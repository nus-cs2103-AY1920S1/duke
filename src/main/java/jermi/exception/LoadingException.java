package jermi.exception;

import static jermi.misc.Constant.EXCEPTION_LOADING_MESSAGE;

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
        super(String.format(EXCEPTION_LOADING_MESSAGE, message));
    }
}
