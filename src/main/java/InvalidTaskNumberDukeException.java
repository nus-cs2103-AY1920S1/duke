/**
 * This is a class for an exception where the task number is invalid.
 * @author Choong Yong Xin
 */

public class InvalidTaskNumberDukeException extends DukeException {
    public InvalidTaskNumberDukeException(String error) {
        super("☹ OOPS!!! The task number is " + error);
    }
}
