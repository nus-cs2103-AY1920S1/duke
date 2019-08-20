public class DukeIllegalDescriptionException extends Exception {
    public DukeIllegalDescriptionException(String act) {
        super("☹ OOPS!!! The description of a "+act+" cannot be empty.");
    }
}
