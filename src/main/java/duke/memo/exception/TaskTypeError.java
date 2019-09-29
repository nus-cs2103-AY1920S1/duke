package duke.memo.exception;

public class TaskTypeError extends DukeException {

    public TaskTypeError() {
        super();
    }

    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
