public class DukeException extends Exception {

    public DukeException (String message) {
        super(message);
    }

    public static DukeException emptyDescription () {
        return new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
