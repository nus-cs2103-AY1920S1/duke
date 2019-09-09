package duke.dukeexception;

/**
 * Class that handles the exceptions thrown by the Duke program.
 */
public class DukeException extends Exception {
    public static final String DONE_ERROR = "☹ OOPS!!! Please specify task to complete";
    public static final String DELETE_ERROR = "☹ OOPS!!! Please specify task to delete";
    public static final String EMPTY_TODO_ERROR = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String EMPTY_DEADLINE_ERROR =
            "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String DATETIME_ERROR =  "☹ OOPS!!! Date and Timing not specified correctly!";
    public static final String EMPTY_EVENT_ERROR = "☹ OOPS!!! The description of a event cannot be empty.";
    public static final String FIND_KEYWORD_ERROR = "Cannot perform search with no keyword!";
    public static final String UNRECOGNIZED_COMMAND_ERROR =
            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String WRITING_ERROR = "Error writing to specified file path";
    public static final String LOADING_ERROR = "Error loading from specified file path";
    /**
     * Class constructor that specifies the exception message associated with
     * this instance.
     *
     * @param message Error message to be printed.
     */
    public DukeException(String message) {
        super(message);
    }
}
