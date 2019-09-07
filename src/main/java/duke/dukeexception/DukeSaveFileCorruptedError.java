package duke.dukeexception;

public class DukeSaveFileCorruptedError extends DukeException {
    public DukeSaveFileCorruptedError() {
        super("The save file is corrupted. Creating new save file...");
    }
}