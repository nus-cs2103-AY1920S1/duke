package duke.exception;

public class MissingDeadlineException extends DukeException {

    public String toString() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }

}
