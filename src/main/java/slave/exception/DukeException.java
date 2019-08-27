package slave.exception;

/**
 * Customised Exception for this particular program
 */
public abstract class DukeException extends Exception {

    /**
     * Constructor
     * @param message Error message
     */
    public DukeException(String message){
        super(message + " The Slave is sad.");
    }

}