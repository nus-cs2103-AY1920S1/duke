package duke.exception;

/** An exception to be thrown when the date is not legal.*/
public class IllegalDateException extends IllegalDescriptionException {
    /**
     * Constructor specifying the message.
     * @param message message about the exception.
     */
    public IllegalDateException(String message) {
        super(message);
    }
}
