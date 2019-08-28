/**
 * This is a class of exception where the description of the command is empty.
 * @author Choong Yong Xin
 */
public class EmptyDescDukeException extends DukeException {
    public EmptyDescDukeException(String command) {
        super("☹ OOPS!!! The description of a " + command + " cannot be empty.");
    }
}
