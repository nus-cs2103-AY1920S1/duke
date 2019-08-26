package task;
import java.lang.Exception;

public class InvalidTaskException extends Exception {
    /**
     * Constructor for this exception.
     * @param msg String msg is passed when this exception is thrown.
     */
    public InvalidTaskException(String msg) {
        super(msg);
    }
}
