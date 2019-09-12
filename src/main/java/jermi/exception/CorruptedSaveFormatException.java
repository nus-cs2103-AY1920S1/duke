package jermi.exception;

import static jermi.misc.Constant.EXCEPTION_CORRUPTED_SAVE_FORMAT_MESSAGE;

/**
 * An exception associated with corrupted save format.
 */
public class CorruptedSaveFormatException extends LoadingException {
    /**
     * Public constructor for class.
     *
     * @param saveFormat The specific line in the file that is causing the error.
     */
    public CorruptedSaveFormatException(String saveFormat) {
        super(String.format(EXCEPTION_CORRUPTED_SAVE_FORMAT_MESSAGE, saveFormat));
    }
}
