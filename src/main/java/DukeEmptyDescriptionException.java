public class DukeEmptyDescriptionException extends DukeException {
    public DukeEmptyDescriptionException(String eventType) {
        super("OOPS!!! The description of a " + eventType + " cannot be empty.");
    }
}
