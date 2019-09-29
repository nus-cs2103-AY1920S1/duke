package q.error;

/**
 * When insufficient arguments are provided.
 */
public class InsufficientArgumentError extends QException {
    /**
     * Constructs an InsufficientArgumentErro().
     */
    public InsufficientArgumentError() {
        super("The description cannot be empty.");
    }
}
