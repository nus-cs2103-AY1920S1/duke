package duke.exception;
//
//import duke.Duke.exception.DukeException;

/**
 * InvalidInputException class extends DukeException and deals with
 * any random inputs that is not within the function of the program.
 */
public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super();
    }
    
    /**
     * Class constructor.
     *
     * @param message message to be tagged to the Duke.exception.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
