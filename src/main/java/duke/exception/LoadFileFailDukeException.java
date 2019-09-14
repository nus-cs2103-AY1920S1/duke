package duke.exception;

public class LoadFileFailDukeException extends DukeException {
    public LoadFileFailDukeException(String filePath) {
        super("Oops... " + filePath + " cannot be loaded.\nIt will be automatically overwritten once you have a list.");
    }
}
