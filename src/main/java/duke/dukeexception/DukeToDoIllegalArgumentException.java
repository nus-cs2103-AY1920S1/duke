package duke.dukeexception;

public class DukeToDoIllegalArgumentException extends DukeException {
    public DukeToDoIllegalArgumentException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}