package duke.memo.exception;

public class TaskNotExistException extends DukeException {

    public TaskNotExistException() {
        super();
    }

    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but there is no such task in the list";
    }
}
