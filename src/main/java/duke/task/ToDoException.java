package duke.task;

/**
 * The ToDoException class handles any incorrect todo input by the user,
 * and prompts them to enter in the correct format.
 */
public class ToDoException extends Exception{
    private final String _noDescription = " OOPS!!! The description of a todo cannot be empty.";
    private String _error;

    /**
     * Constructs a ToDoException object.
     * @param message the message linked to the exception
     */
    public ToDoException(String message) {
        this._error = message;
    }

    /**
     * Gets the error message tied to the exception found.
     * @return a string of error message for the exception
     * found in the ToDo object.
     */
    public String getMessage() {
        return _noDescription;
    }
}
