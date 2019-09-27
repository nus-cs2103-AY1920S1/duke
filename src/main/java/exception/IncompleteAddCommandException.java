package exception;

/**
 * IncompleteAddCommandException serves as the specialised error for
 * any tasks that are input in the wrong format
 */

public class IncompleteAddCommandException extends Exception {
    /**
     * Constructor giver the error message based on each task from Parser
     */

    public IncompleteAddCommandException(String message) {
        super(message);
    }
}