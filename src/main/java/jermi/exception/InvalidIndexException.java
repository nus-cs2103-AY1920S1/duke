package jermi.exception;

import static jermi.misc.Constant.EXCEPTION_INVALID_INDEX_MESSAGE;

/**
 * An exception associated with invalid index given.
 */
public class InvalidIndexException extends JermiException {
    /**
     * Public constructor for class.
     */
    public InvalidIndexException() {
        super(EXCEPTION_INVALID_INDEX_MESSAGE);
    }
}
