package duke.exception;

/**
 * Represents a custom exceptions class that extends from DukeException.
 * This exception is thrown when there are missing inputs such as the deadline or event time/day.
 */
public class MissingInputException extends DukeException {

    /**
     * Constructs a new exception with the specified detail message.
     * @param errorMessage the detail message. It is saved for later retrieval by the Throwable.getMessage() method.
     */
    public MissingInputException(String errorMessage) {
        super(errorMessage);
    }

}
