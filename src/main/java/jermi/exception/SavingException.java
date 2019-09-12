package jermi.exception;

import static jermi.misc.Constant.EXCEPTION_SAVING_MESSAGE;

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
        super(String.format(EXCEPTION_SAVING_MESSAGE, message));
    }
}
