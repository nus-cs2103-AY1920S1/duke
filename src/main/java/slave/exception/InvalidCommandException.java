package slave.exception;

/**
 * Represents an invalid command exception
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructor
     * @param message Error message
     */
    public InvalidCommandException(String message){
        super(String.format("%s command doesn't exist!", message));
    }
}
