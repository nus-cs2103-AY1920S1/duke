package task;
import java.lang.Exception;

public class InsufficientTaskArgumentException extends Exception {
    /**
     * Constructor for this exception.
     * @param msg String msg is passed when this exception is thrown.
     */
    public InsufficientTaskArgumentException(String msg) {
        super(msg);
    }
}
