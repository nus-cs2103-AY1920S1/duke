package exception;

/**
 * An exception which only occurs if an an error is encountered when a UnknownCommand is executed.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {

    }

    @Override
    public String toString() {
        return " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
