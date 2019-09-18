package duke.exceptions;

/**
 * A custom exception class detailing the error messages.
 */
@SuppressWarnings("serial")
public class DukeException extends Exception{
    
    /**
     * 
     * @param errorMessage takes in the error message and instantiates its parent class
     */
    public DukeException(String errorMessage){
        super(errorMessage);
    }
}