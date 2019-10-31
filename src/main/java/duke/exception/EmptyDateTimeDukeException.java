package duke.exception;

public class EmptyDateTimeDukeException extends DukeException {

    /**
     * Creates an EmptyDateTimeDukeException.
     */
    public EmptyDateTimeDukeException() {
        super("OOPS!!! The date and time inputs cannot empty!\n");
    }
}
