public class DukeMissingDescriptionException extends DukeException {
    public DukeMissingDescriptionException(String eventType) {
        super("OOPS!!! The description of a " + eventType + " has missing information.");
    }
}

