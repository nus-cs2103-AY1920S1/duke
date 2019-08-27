import java.lang.Exception;

/** 
 * Exception thrown when User input cannot be read by Duke.
 */
public class DukeException extends Exception {
    public DukeException (String errorMessage) {
        super(errorMessage);
    }
}
