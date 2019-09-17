package duke.exception;

public class DukeIllegalDescriptionException extends IllegalArgumentException {
    public DukeIllegalDescriptionException(String act) {
        super("☹ OOPS!!! The description of a " + act + " cannot be empty.");
    }
}
