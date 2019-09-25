package exception;

public class DoneException extends DukeException {
    public DoneException() {

    }

    @Override
    public String toString() {
        return " ☹ OOPS!!! You must specify a Task to be done!";
    }
}
