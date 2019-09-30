package duke.exception;
//
//import duke.Duke.exception.DukeException;

/**
 * InvalidDescriptionException class extends DukeException and deals with
 * Duke.tasks with the wrong description given by the input in the program.
 */
public class InvalidDescriptionException extends DukeException {
    
    /**
     * Class constructor.
     *
     * @param message message to be tagged to the Duke.exception.
     */
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
