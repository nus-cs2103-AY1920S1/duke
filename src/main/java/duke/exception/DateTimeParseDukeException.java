package duke.exception;

public class DateTimeParseDukeException extends DukeException {
    public DateTimeParseDukeException() {
        super("Please follow according to the date format: YYYY-MM-DD'T'HH:mm:ss\n");
    }
}
