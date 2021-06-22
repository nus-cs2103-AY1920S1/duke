package exception;

/**
 * Responsible for problems occured during Delete task handling.
 */
public class DeleteException extends DukeException {

    public DeleteException() {
        super("OOPS!!! The description of a delete cannot be empty.");
    }

    public DeleteException(String msg) {
        super(msg);
    }

}