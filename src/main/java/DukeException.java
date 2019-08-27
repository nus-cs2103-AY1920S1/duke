import java.lang.Exception;

/** 
 * Exception thrown when User input cannot be read by Duke.
 */
public class DukeException extends Exception {
    private static final long serialVersionUID = 223646L;
    
    public DukeException (String errorMessage) {
        super(errorMessage);
    }
}
