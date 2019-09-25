package exception;

public class TaskException extends DukeException {
    public TaskException() {

    }

    @Override
    public String toString() {
        return " ☹ OOPS!!! The description of a Task cannot be empty.";
    }
}
