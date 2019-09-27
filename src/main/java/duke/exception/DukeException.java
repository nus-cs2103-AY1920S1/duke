package duke.exception;

/**
 * Represents a custom exceptions class to represent exceptions specific to Duke.
 */
public class DukeException extends Exception {

    protected String errorMessage;

    /**
     * Constructs a new exception with the specified detail message.
     * @param errorMessage the detail message. It is saved for later retrieval by the Throwable.getMessage() method.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
