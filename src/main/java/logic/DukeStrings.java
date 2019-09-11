package logic;

public class DukeStrings {
    public static String INVALID_TASK_NUMBER = "Please state a valid task number";
    public static String NONEXISTENT_TASK_NUMBER = "That task number does not exist, please try again";
    public static String TODO_EMPTY = "☹ OOPS!!! The description of a todo cannot be empty";
    public static String DEADLINE_EMPTY = "☹ OOPS!!! The description of a deadline cannot be empty";
    public static String EVENT_EMPTY = "☹ OOPS!!! The description of an event cannot be empty";
    public static String DEADLINE_WRONG_FORMAT = "Invalid format. Please include '/by' to state your deadline"
            + "\nE.g. deadline work /by 12/7/2019 2000";
    public static String EVENT_WRONG_FORMAT = "Invalid format. Please follow the format:"
            + "\nevent [description] /at DD/MM/YYYY HHMM - DD/MM/YYYY HHMM";
    public static String EVENT_NOT_CHRONO = "☹ OOPS!!! Start DateTime cannot be after End DateTime!";
    public static String INVALID_DATETIME_FORMAT = "Invalid Date-Time format.\n"
            + "Please use DD/MM/YYYY HHMM E.g. [2/12/2019 1800]";
    public static String FIND_EMPTY = "☹ OOPS!!! Please enter a keyword";
    public static String UNKNOWN_INPUT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
}
