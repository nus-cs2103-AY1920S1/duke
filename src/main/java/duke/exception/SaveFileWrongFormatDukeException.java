package duke.exception;

/**
 * An exception when the save file for duke contains information that cannot be parsed.
 */
public class SaveFileWrongFormatDukeException extends DukeException {
    public SaveFileWrongFormatDukeException(String s) {
        super(s);
    }
}
