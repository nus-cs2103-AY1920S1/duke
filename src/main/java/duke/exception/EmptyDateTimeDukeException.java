package duke.exception;

public class EmptyDateTimeDukeException extends DukeException {

    public EmptyDateTimeDukeException() {
        super("     ☹ OOPS!!! The date and time inputs cannot empty!\n");
    }
}
