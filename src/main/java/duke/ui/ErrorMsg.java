package duke.ui;

public enum ErrorMsg {
    UNKNOWN_COMMAND("I'm sorry, but I don't know what that means :-("),
    BAD_DATE_FORMAT("Deadline is ill formatted. Example: deadline return book /by 2/12/2019 1800"),
    INVALID_DATE_FORMAT("Failed to add task because date is not in the right format (should be in dd/MM/yyyy HHmm)"),
    INVALID_NUMBER_ARGS__FOR_INDEX_CMD("Invalid number of arguments in an index based command"),
    INVALID_INDEX("The index is invalid."),
    ERROR_IN_PARSING_SAVEFILE("Error in parsing line from save file"),
    ERROR_IN_WRITING_TO_SAVEFILE("An exception occurred when saving to file.");


    private String description = null;

    private ErrorMsg(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }
}
