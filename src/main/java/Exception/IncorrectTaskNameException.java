package Exception;

/**
 * Handles Incorrect Task Name Exceptions of the Duke Project
 */
public class IncorrectTaskNameException extends Exception{

    public IncorrectTaskNameException(String errorMessage){
        super(errorMessage);
    }
}
