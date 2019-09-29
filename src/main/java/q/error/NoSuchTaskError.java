package q.error;

/**
 * When there is no such task.
 */
public class NoSuchTaskError extends QException {
    /**
     * Constructs a NoSuchTaskError.
     */
    public NoSuchTaskError() {
        super("There is no such task.");
    }
}
