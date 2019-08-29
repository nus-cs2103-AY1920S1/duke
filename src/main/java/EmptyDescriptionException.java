package duke.exception;

import duke.exception.DukeException;
/**
 * EmptyDescriptionException class extends DukeException and deals with
 * tasks without any description given by the input in the program.
 */
public class EmptyDescriptionException extends DukeException{
    
    /**
     * Class constructor
     *
     * @param message message to be tagged to the exception.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
