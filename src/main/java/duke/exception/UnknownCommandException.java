package duke.exception;
/**
 * Represents an Exception that is thrown when the input does not result in the execution of a command.
 */
public class UnknownCommandException extends DukeException {

    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

}
