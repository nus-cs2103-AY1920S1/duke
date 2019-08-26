public class DukeIllegalDescriptionException extends Exception{

    public DukeIllegalDescriptionException(String cmd) {
        super("OOPS!!! The description of a " + cmd + " is invalid.");
    }
}
