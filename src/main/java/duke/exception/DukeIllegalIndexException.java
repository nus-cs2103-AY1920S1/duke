package duke.exception;

public class DukeIllegalIndexException extends IllegalArgumentException {
    public DukeIllegalIndexException() {
        super("This index is not valid!");
    }
}
