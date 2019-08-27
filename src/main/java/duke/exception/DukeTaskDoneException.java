package duke.exception;
public class DukeTaskDoneException extends DukeException{
    public DukeTaskDoneException() {
        super("OOPS!!! Task is already completed.");
    }
}
