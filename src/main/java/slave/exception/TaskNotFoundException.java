package slave.exception;

public class TaskNotFoundException extends DukeException {

    public TaskNotFoundException(String message){
        super(String.format("%s cannot be found.", message));
    }
}
