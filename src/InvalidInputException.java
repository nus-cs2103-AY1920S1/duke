package main.java.exception;
//
//import duke.exception.DukeException;
/**
 * InvalidInputException class extends DukeException and deals with
 * any random inputs that is not within the function of the program.
 */
public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super();
    }
    
    /**
     * Class constructor
     *
     * @param message message to be tagged to the exception.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
