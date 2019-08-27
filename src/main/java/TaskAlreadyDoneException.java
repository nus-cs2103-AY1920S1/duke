public class TaskAlreadyDoneException extends DukeException {

    public TaskAlreadyDoneException(String message){
        super(String.format("%s already done!", message));
    }
}
