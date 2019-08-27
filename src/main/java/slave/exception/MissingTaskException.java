package slave.exception;

public class MissingTaskException extends DukeException {

    public MissingTaskException(){
        super("Task is missing! Please specify the task!");
    }
}
