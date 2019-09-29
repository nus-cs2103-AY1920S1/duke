package duke.exception;

public enum ExceptionType {
    INVALID_ID("Oops! You entered an invalid task ID!"),
    ID_BLANK("Oops! You did not enter a task ID!"),
    NO_MATCHING_TASKS("There are no tasks matching your query :("),
    TASK_LIST_EMPTY("The task list is empty."),
    DESCRIPTION_BLANK("Oops! You did not enter a description!"),
    INVALID_COMMAND("Command not recognised!");

    private final String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
