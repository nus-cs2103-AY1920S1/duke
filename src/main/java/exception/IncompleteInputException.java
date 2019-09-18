package exception;

/**
 * IncompleteInputException serves as the specialised error for
 * any tasks that are input in the wrong format
 */

public class IncompleteInputException extends Exception {
    /**
     * Constructor giver the error message based on each task from Parser
     */

    public IncompleteInputException(String message) {
        super(message);
    }
}