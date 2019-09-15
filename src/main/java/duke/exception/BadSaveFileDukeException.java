package duke.exception;

public class BadSaveFileDukeException extends DukeException {
    public BadSaveFileDukeException() {
        super("Bad save file, unable to load data.\n");
    }
}
