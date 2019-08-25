package duke.exception;

public class TooManyTasksException extends DukeException {

    public String toString() {
        return "OOPS!!! You can have only at most 100 tasks!";
    }
}
