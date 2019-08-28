package Exception;

public class EmptyTodoDescriptionException extends Exception{

    public EmptyTodoDescriptionException(String errorMessage){
        super(errorMessage);
    }
}
