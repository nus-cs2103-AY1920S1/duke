public class DukeEmptyException extends DukeException {
    public DukeEmptyException(String errorMessage) {
        super("The description of a " + errorMessage + " cannot be empty.");
    }
}
