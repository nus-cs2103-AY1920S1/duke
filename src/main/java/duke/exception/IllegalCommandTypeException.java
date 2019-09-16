package duke.exception;

public class IllegalCommandTypeException extends Exception {
    /**
     * Constructor specifying the message.
     * @param message message about the exception.
     */
    public IllegalCommandTypeException(String message) {
        super(message);
    }
}
