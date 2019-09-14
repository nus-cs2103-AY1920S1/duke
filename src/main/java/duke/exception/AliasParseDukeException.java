package duke.exception;

public class AliasParseDukeException extends DukeException {
    public AliasParseDukeException() {
        super("Alias cannot be resolved.\nPlease provide alias of Place or latitude with longitude.");
    }
}
