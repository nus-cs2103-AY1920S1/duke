import java.lang.Exception;

/** 
 * Exception thrown when User input cannot be read by Duke.
 */
public class DukeException extends Exception {
    private static final long serialVersionUID = 223646L;
    
    /**
     * Creates a new DukeException with the input errorMessage.
     * 
     * @param errorMessage The message specifying Duke's response to the user's incorrect input.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
