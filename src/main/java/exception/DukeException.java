package exception;

/**
 * Represents all exception unique to Duke.
 */
public class DukeException extends RuntimeException {
    protected ErrorType type;

    /**
     * Initializes DukeException with error type.
     * @param input the error type
     */
    public DukeException(ErrorType input) {
        this.type = input;
    }

    /**
     * Documents user input error type.
     */
    public enum ErrorType {
        INVALID_NUMBER, EMPTY_TODO, EMPTY_DEADLINE_DATE, EMPTY_EVENT_DATE, GIBBERISH, INVALID_EDIT_FIELD,
        EMPTY_EDIT_FIELD, INVALID_TODO_EDIT_FIELD
    }

    /**
     * Returns String error message.
     *
     * @return error message
     */
    @Override
    public String getMessage() {
        switch (this.type) {
        case INVALID_NUMBER:
            return "Please enter a valid task number.";
        case EMPTY_TODO:
            return "The description of a todo cannot be empty.";
        case EMPTY_DEADLINE_DATE:
            return "Please enter a date for your deadline in the form '/by <your date here>' without <>.";
        case EMPTY_EVENT_DATE:
            return "Please enter a date for your event in the form '/at <your date here>' without <>.";
        case INVALID_EDIT_FIELD:
            return "Please enter a valid edit field: date or description.";
        case EMPTY_EDIT_FIELD:
            return "Please enter details of new change.";
        case INVALID_TODO_EDIT_FIELD:
            return "Todo only has description field. Please change your edit field to description.";
        default:
            return "I'm sorry, but I don't know what that means :-(";
        }
    }
}
