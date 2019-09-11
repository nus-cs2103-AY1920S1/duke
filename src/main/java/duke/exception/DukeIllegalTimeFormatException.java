package duke.exception;

public class DukeIllegalTimeFormatException extends IllegalArgumentException {
    public DukeIllegalTimeFormatException() {
        super("Please enter the time in YYYY/MM/DD HH:mm");
    }
}
