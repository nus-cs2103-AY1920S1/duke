public class EmptyDescDukeException extends DukeException {
    public EmptyDescDukeException(String command) {
        super("☹ OOPS!!! The description of a " + command + " cannot be empty.");
    }
}
