package duke.exception;

/** An exception to be thrown when the time is not legal.*/
public class IllegalTimeException extends IllegalDescriptionException {
    /**
     * Constructor specifying the message.
     * @param message message about the exception.
     */
    public IllegalTimeException(String message) {
        super(message);
    }
}
