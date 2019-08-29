package main.java.exception;
//
//import duke.exception.DukeException;
/**
 * InvalidDescriptionException class extends DukeException and deals with
 * tasks with the wrong description given by the input in the program.
 */
public class InvalidDescriptionException extends DukeException {
    
    /**
     * Class constructor
     *
     * @param message message to be tagged to the exception.
     */
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
