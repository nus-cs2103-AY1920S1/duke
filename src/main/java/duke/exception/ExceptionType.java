package duke.exception;

public enum ExceptionType {
    // invalid
    INVALID_ID("Oops! You entered an invalid task ID!"),
    INVALID_COMMAND("Command not recognised!"),
    INVALID_DATE("Oops! You did not enter the date in an appropriate format.\n" +
        "Try: DD/MM/YYYY HHmm instead."),
    // blank
    ID_BLANK("Oops! You did not enter a task ID!"),
    DESCRIPTION_BLANK("Oops! You did not enter a description!"),
    DEADLINE_BLANK("Oops! You did not enter a deadline!"),
    KEYWORD_BLANK("Oops! You did not enter a keyword!"),
    // nothing to return
    NO_MATCHING_TASKS("There are no tasks matching your query :("),
    TASK_LIST_EMPTY("The task list is empty."),;

    private final String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
