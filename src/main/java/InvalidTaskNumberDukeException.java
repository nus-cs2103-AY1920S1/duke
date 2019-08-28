public class InvalidTaskNumberDukeException extends DukeException {
    public InvalidTaskNumberDukeException(String error) {
        super("☹ OOPS!!! The task number is " + error);
    }
}
