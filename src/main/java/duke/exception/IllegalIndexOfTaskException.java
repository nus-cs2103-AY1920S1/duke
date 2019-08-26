package duke.exception;

/** An exception to be thrown when the task index is illegal.*/
public class IllegalIndexOfTaskException extends Exception {
    public IllegalIndexOfTaskException(String message) {
        super(message);
    }
}
