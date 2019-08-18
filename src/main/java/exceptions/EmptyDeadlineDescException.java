package exceptions;

public class EmptyDeadlineDescException extends DukeException {

    public EmptyDeadlineDescException() {
        super(" ☹ OOPS!!! The description of a deadline cannot be empty.");
    }
}
