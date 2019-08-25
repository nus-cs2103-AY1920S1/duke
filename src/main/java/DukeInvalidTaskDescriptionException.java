public class DukeInvalidTaskDescriptionException extends DukeException {

    public DukeInvalidTaskDescriptionException(String msg) {
        super("☹ OOPS!!! The description of a " + msg + " task cannot be empty.");
    }

}
