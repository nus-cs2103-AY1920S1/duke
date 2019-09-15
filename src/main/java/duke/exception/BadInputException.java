package duke.exception;

public class BadInputException extends DukeException {
    public BadInputException() {
        super("     ☹ OOPS!!! The description of a task cannot be empty.\n");
    }
}
