package duke.exception;

public class FileSaveException extends DukeException {

    @Override
    public String toString() {
        return String.format("%s Failed to save to file!", super.toString());
    }
}
