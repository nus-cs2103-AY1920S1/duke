package Exception;

/**
 * Handles Empty Deadline Description Exceptions of the Duke Project
 */
public class EmptyDeadlineDescriptionException extends Exception{

    public EmptyDeadlineDescriptionException(String errorMessage){
        super(errorMessage);
    }
}
