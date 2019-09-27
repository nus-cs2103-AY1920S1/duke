package duke.exception;

/**
 * Represents a custom exceptions class that extends from DukeException.
 * This exception is thrown when an invalid command is given to Duke.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructs a new exception with the specified detail message.
     * @param errorMessage the detail message. It is saved for later retrieval by the Throwable.getMessage() method.
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }

}
