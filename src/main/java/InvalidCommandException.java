/**
 * Class representation of the Exception thrown when Duke is given an invalid command
 */
public class InvalidCommandException extends DukeException {
    /**
     * 
     * @param message Exception message
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}