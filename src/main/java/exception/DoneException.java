package exception;

/**
 * An exception which only occurs if an an error is encountered when a Done Command is executed.
 */
public class DoneException extends DukeException {
    public DoneException() {

    }

    @Override
    public String toString() {
        return " ☹ OOPS!!! You must specify a Task to be done!";
    }
}
