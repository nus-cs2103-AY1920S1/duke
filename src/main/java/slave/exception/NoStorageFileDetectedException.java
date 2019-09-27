package slave.exception;

/**
 * Exception for when storage file cannot be found.
 */
public class NoStorageFileDetectedException extends KappaException {

    /**
     * Constructor for NoStorageFileDetectedException.
     */
    public NoStorageFileDetectedException() {
        super("No storage file detected!");
    }
}
