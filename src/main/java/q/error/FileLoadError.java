package q.error;

/**
 * When there is an error in loading the file.
 */
public class FileLoadError extends QException {
    /**
     * Constructs a FileLoadError.
     */
    public FileLoadError() {
        super("There was a problem loading the saved list of tasks.");
    }
}
