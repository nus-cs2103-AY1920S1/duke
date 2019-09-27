package exception;

/**
 * IncompleteAddCommandException serves as the specialised error for any incomplete userInput.
 */

public class IncompleteAddCommandException extends Exception {
    /**
     * Constructor gives the error message based on each task from Parser.
     */

    public IncompleteAddCommandException(String message) {
        super(message);
    }
}