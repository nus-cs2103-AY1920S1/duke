package exception;

public class DeleteException extends DukeException {
    public DeleteException() {};

    @Override
    public String toString() {
        return " ☹ OOPS!!! There was an error with deleting!"; 
    }
}
