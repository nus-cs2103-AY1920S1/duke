package duke.exception;

/**
 * Represents a custom exceptions class that extends from DukeException.
 * This exception is thrown when a task is missing its description.
 */
public class MissingDescriptionException extends DukeException {

    /**
     * Constructs a new exception with the specified detail message.
     * @param errorMessage the detail message. It is saved for later retrieval by the Throwable.getMessage() method.
     */
    public MissingDescriptionException(String errorMessage) {
        super(errorMessage);
    }

}
