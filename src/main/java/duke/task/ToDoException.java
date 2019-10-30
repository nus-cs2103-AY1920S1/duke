package duke.task;

/**
 * The ToDoException class handles any incorrect todo input by the user,
 * and prompts them to enter in the correct format.
 */
public class ToDoException extends Exception {
    private static final String MISSING_DESCRIPTION =
            " OOPS!!! The description of a todo cannot be empty.";
    private String error;

    /**
     * Constructs a ToDoException object.
     * @param message the message linked to the exception
     */
    public ToDoException(String message) {
        this.error = message;
    }

    /**
     * Gets the error message tied to the exception found.
     * @return a string of error message for the exception
     * found in the ToDo object.
     */
    public String getMessage() {
        return MISSING_DESCRIPTION;
    }
}
