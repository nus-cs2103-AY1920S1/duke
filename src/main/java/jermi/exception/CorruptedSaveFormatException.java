package jermi.exception;

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
        super(String.format("Save format '%s' is corrupted.", saveFormat));
    }
}
