package duke.exception;

/**
 * Represents an Exception that is thrown when the name of the ToDo Task is not given or valid.
 */
public class InvalidToDoException extends DukeException {

    public String toString() {
        return "OOPS!!! The description of a todo cannot be empty";
    }

}
