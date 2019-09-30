package duke.exception;

//import main.java.Duke.exception.DukeException;

/**
 * EmptyDescriptionException class extends DukeException and deals with
 * Duke.tasks without any description given by the input in the program.
 */
public class EmptyDescriptionException extends DukeException {
    
    /**
     * Class constructor.
     *
     * @param message message to be tagged to the Duke.exception.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
