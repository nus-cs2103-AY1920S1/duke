package dose.util.exception;

/**
 * Represents different types of Exceptions unique to the application.
 */
public enum ExceptionType {
    // invalid
    INVALID_ID("Oops! You entered an invalid task ID!"),
    INVALID_COMMAND("Oops! I don't know what that means. Try using help to see what I can do."),
    INVALID_DATE("Oops! You did not enter the date in an appropriate format.\n" +
        "Try: DD/MM/YYYY HHmm instead."),
    // blank
    ID_BLANK("Oops! You did not enter a task ID!"),
    DESCRIPTION_BLANK("Oops! You did not enter a description!"),
    DEADLINE_BLANK("Oops! You did not enter a deadline!"),
    KEYWORD_BLANK("Oops! You did not enter a keyword!"),
    TAG_BLANK("Oops! You did not enter a tag!"),
    // nothing to return
    NO_MATCHING_TASKS("There are no tasks matching your query :("),
    TASK_LIST_EMPTY("The task list is empty.");

    private final String message;

    ExceptionType(String message) {
        this.message = message;
    }

    /**
     * Returns a message describing the Exception, to be displayed in the UI.
     * @return Message describing the Exception, to be displayed in the UI.
     */
    public String getMessage() {
        return this.message;
    }
}
