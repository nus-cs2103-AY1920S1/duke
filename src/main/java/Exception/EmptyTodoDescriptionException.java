package Exception;

/**
 * Handles Empty Todo Descriptions Exceptions of the Duke Project
 */
public class EmptyTodoDescriptionException extends Exception{

    public EmptyTodoDescriptionException(String errorMessage){
        super(errorMessage);
    }
}
