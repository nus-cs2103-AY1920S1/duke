package exception;

public class EmptyToDoDescriptionException extends DukeException{
    public EmptyToDoDescriptionException(String exceptionMsg) {
        super(exceptionMsg);
    }
}