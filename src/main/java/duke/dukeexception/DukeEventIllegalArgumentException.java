package duke.dukeexception;

public class DukeEventIllegalArgumentException extends DukeException {
    public DukeEventIllegalArgumentException(String fieldName) {
        super("☹ OOPS!!! The " + fieldName + " of an event cannot be empty.");
    }
}