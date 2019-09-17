package duke.exception;

public class EmptyDateTimeDukeException extends DukeException {
    public EmptyDateTimeDukeException() {
        super("\u2639 OOPS!!! The date and time inputs cannot empty!\n");
    }
}
