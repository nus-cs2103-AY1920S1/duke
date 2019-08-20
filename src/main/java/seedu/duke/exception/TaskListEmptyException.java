package seedu.duke.exception;

public class TaskListEmptyException extends Exception{
    public TaskListEmptyException(String msg) {
        super(msg);
    }
}
