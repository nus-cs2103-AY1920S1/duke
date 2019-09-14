package duke.exception;

public class PlaceParseDukeException extends DukeException {
    public PlaceParseDukeException() {
        super("Given latitude and/or longitude cannot be resolved. Please try again.");
    }
}
