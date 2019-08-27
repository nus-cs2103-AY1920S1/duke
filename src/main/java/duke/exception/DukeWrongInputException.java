package duke.exception;

/**
 * Represents the exception that occurs when user inputs a command that is not valid.
 */
public class DukeWrongInputException extends DukeException {
    public DukeWrongInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
