package duke.exception;

public class CorruptedFileDukeException extends DukeException {
    public CorruptedFileDukeException() {
        super("Save file data could be corrupted & cannot be parsed.");
    }
}
