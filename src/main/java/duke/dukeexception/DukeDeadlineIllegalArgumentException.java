package duke.dukeexception;

public class DukeDeadlineIllegalArgumentException extends DukeException {
    public DukeDeadlineIllegalArgumentException(String fieldName) {
        super("☹ OOPS!!! The " + fieldName + " of a deadline cannot be empty.");
    }
}
