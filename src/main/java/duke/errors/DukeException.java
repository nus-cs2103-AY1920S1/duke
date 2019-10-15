package duke.errors;


/**
 * Represents an Exception class in Duke.
 */
public class DukeException extends Exception {

    private DukeExceptionType type;

    public DukeException(String error, DukeExceptionType type){
        super(error);
        this.type = type;

        assert type != null;
    }

	/**
	 * Specifies the message to be printed based on different Exception types
	 * @return String which is the message of the exception.
	 */
    public String getMessage() {
    	switch (type) {
    		case INVALID_COMMAND:
    			return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    		case NOT_INTEGER:
    			return "Invalid input, must be an integer!!";
    		case MISSING_TASK:
    			return "No such task";
			case INVALID_DATE_TIME_FORMAT:
				return super.getMessage();
            case FILE_NOT_FOUND:
                return "File not found!!";
			case TASK_NOT_FOUND:
				return "Task not found!!";
			case NOT_SINGLE_WORD:
				return "Must be a single keyword";
			case TASK_ALREADY_DONE:
				return "Task already done!";
			default:
    			return "Unknown error! Please try again.";
    	}
    }
}
