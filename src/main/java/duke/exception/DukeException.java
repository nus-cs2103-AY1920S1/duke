package duke.exception;

/**
 * The base class for other checked exceptions in the chatbot application.
 */
public class DukeException extends Exception {

    /**
     * Public constructor for DukeException.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
