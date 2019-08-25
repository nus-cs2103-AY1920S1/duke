package duke.exception;

/**
 * Represents an Exception to be thrown when the user tries to add more than 100 tasks.
 */
public class TooManyTasksException extends DukeException {

    public String toString() {
        return "OOPS!!! You can have only at most 100 tasks!";
    }
}
