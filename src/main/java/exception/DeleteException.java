package exception;

/**
 * An exception which only occurs if an an error is encountered when a Delete Command is executed.
 */
public class DeleteException extends DukeException {
    public DeleteException() {

    }

    @Override
    public String toString() {
        return " ☹ OOPS!!! There was an error with deleting!";
    }
}
