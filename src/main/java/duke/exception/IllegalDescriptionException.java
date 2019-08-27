package duke.exception;

/** An exception to be thrown when the task description is not legal.*/
public class IllegalDescriptionException extends Exception {
    /**
     * Constructor specifying the message.
     * @param message message about the exception.
     */
    public IllegalDescriptionException(String message) {
        super(message);
    }
}
