package duke.exception;

/** An exception to be thrown when the task index is illegal.*/
public class IllegalIndexOfTaskException extends Exception {
    /**
     * Constructor specifying the message.
     * @param message message about the exception.
     */
    public IllegalIndexOfTaskException(String message) {
        super(message);
    }
}
