package jermi.exception;

import static jermi.misc.Constant.EXCEPTION_EMPTY_DESCRIPTION_MESSAGE;

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
        super(String.format(EXCEPTION_EMPTY_DESCRIPTION_MESSAGE, message));
    }
}
