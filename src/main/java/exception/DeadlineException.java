package exception;

/**
 * Responsible for problems occured during Deadline task handling.
 */
public class DeadlineException extends DukeException {

    public DeadlineException() {
        super("OOPS!!! The description of a deadline cannot be empty.");
    }

    public DeadlineException(String msg) {
        super(msg);
    }

}