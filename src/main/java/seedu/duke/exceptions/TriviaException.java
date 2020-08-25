package seedu.duke.exceptions;

/**
 * Exception class for Trivia-specific errors.
 */
public class TriviaException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for exception with a message.
     *
     * @param message Message of the exception.
     */
    public TriviaException(String message) {
        super(message);
    }

    /**
     * Overloaded constructor that does not take in a message.
     */
    public TriviaException() {

    }

}