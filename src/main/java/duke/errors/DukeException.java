package duke.errors;


/**
 * Represents an Exception class in Duke.
 */
public class DukeException extends Exception {

    private DukeExceptionType type;

    public DukeException(String error, DukeExceptionType type){
        super(error);
        this.type = type;
    }

	/**
	 * Specifies the message to be printed based on different Exception types
	 * @return String which is the message of the exception.
	 */
    public String getMessage() {
    	switch (type) {
    		case INVALIDCOMMAND:
    			return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    		case NOTINTEGER:
    			return "☹ Invalid input, must be an integer!!";
    		case MISSINGTASK:
    			return "☹ No such task";
    		case GENERALMISTAKE:
    			return super.getMessage();
            case FILENOTFOUND: 
                return "File not found!!";
			case TASKNOTFOUND:
				return "Task not found!!";
			case NOTSINGLEWORD:
				return "Must be a single keyword";
    		default:
    			return "Unknown error! Please try again.";
    	}
    }
}
