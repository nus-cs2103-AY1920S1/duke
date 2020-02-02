package duke.exception;

/** An exception to be thrown when the command is not legal.*/
public class IllegalCommandException extends Exception {
    /**
     * Constructor specifying the message.
     * @param message message about the exception.
     */
    public IllegalCommandException(String message) {
        super(message);
    }
}
