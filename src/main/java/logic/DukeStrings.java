package logic;

public class DukeStrings {
    public static final String CONTACT_EMPTY = "☹ OOPS!!! You cannot add an empty contact";
    public static final String CONTACT_WRONG_FORMAT = "Invalid Contact Format"
            + "\nPlease use [{name}, {relationship}, {Contact Number}, {Email}]";
    public static final String INVALID_NUMBER = "Please state a valid number";
    public static final String NONEXISTENT_NUMBER = "That number does not exist on the list, please try again";
    public static final String TODO_EMPTY = "☹ OOPS!!! The description of a todo cannot be empty";
    public static final String DEADLINE_EMPTY = "☹ OOPS!!! The description of a deadline cannot be empty";
    public static final String EVENT_EMPTY = "☹ OOPS!!! The description of an event cannot be empty";
    public static final String DEADLINE_WRONG_FORMAT = "Invalid format. Please include '/by' to state your deadline"
            + "\nE.g. deadline work /by 12/7/2019 2000";
    public static final String EVENT_WRONG_FORMAT = "Invalid format. Please follow the format:"
            + "\nevent [description] /at DD/MM/YYYY HHMM - DD/MM/YYYY HHMM";
    public static final String EVENT_NOT_CHRONO = "☹ OOPS!!! Start DateTime cannot be after End DateTime!";
    public static final String INVALID_DATETIME_FORMAT = "Invalid Date-Time format.\n"
            + "Please use DD/MM/YYYY HHMM E.g. [2/12/2019 1800]";
    public static final String FIND_EMPTY = "☹ OOPS!!! Please enter a keyword";
    public static final String UNKNOWN_INPUT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    public static final String INVALID_CONTACT_NUMBER = "Please state a valid contact number";
}
