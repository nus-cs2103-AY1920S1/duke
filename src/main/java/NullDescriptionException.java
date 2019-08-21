public class NullDescriptionException extends DukeException {
    public NullDescriptionException(String task) {
        super("The description of a " + task + " cannot be empty.");
    }
}
