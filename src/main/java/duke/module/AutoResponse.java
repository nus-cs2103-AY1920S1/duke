package duke.module;

public class AutoResponse {

    public static final String DUKE_LOGO = " ____        _        \n"
            + "    |  _ \\ _   _| | _____ \n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";

    public static final String DUKE_HELLO = "Hello! I'm Duke!\n    What can I do for you?";
    public static final String DUKE_BYE = "Bye. Hope to see you again soon!";
    public static final String DUKE_LINE = "    _________________________________________________________________";
    public static final String DUKE_TAB4 = "    ";
    public static final String DUKE_ADD_TASK = "Got it. I've added this task:";
    public static final String DUKE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";
    public static final String DUKE_DELETE_TASK = "Noted. I've removed this task:";
    public static final String DUKE_DELETE_ALL_TASKS = "Noted. I've removed all tasks.";
    public static final String DUKE_MARK_AS_DONE = "Nice! I've marked this task as done:";
    public static final String DUKE_DONE_ALL_TASKS = "Nice! All of your tasks are marked as done.";
    public static final String DUKE_FOUND_TASKS = "Here are the matching tasks in your list:";
    public static final String DUKE_NO_FOUND_TASKS = "There were no matching tasks for the keyword: ";
    public static final String DUKE_LIST_TASKS = "Here are the tasks in your list:";
    public static final String DUKE_NO_TASKS = "You currently have no tasks in your list.";

    public static final String ERROR_MISSING_TASK_DESCRIPTION = "☹ OOPS!!! The description of a task "
            + "cannot be empty.";
    public static final String ERROR_MISSING_DESCRIPTION_AND_DATE = "☹ OOPS!!! Description and dates of a task "
            + "cannot be empty.";
    public static final String ERROR_MISSING_DEADLINE_DATE = "☹ OOPS!!! Deadline dates must be "
            + "specified after \"/by.\"";
    public static final String ERROR_MISSING_EVENT_DATE = "☹ OOPS!!! Event dates must be specified after \"/at.\"";
    public static final String ERROR_MISSING_KEYWORD = "☹ OOPS!!! Please include a phrase to search for.";
    public static final String ERROR_MISSING_INDEX = "☹ OOPS!!! Please include an index.";
    public static final String ERROR_ILLEGAL_INDEX = "☹ OOPS!!! The index must be a number "
            + "separated by one whitespace.";
    public static final String ERROR_ILLEGAL_YEAR = "☹ OOPS!!! Year should be a number greater than 0";
    public static final String ERROR_ILLEGAL_MONTH = "☹ OOPS!!! Month should be a number between 1 and 12.";
    public static final String ERROR_ILLEGAL_DAY = "☹ OOPS!!! Date must be greater than 0 (and less than 31).";
    public static final String ERROR_DAY_MONTH_MISMATCH = "☹ OOPS!!! Given date does not exist in given month.";
    public static final String ERROR_ILLEGAL_TIME = "☹ OOPS!!! Hour must be between 0 and 23,\n"
            + "      and minute must be between 0 and 59.";
    public static final String ERROR_DATE_FORMAT = "☹ OOPS!!! Date must be in MM/DD/YYYY HHMM format.";
    public static final String ERROR_SAVE_FILE_FORMAT = "☹ OOPS!!! Saved file contains illegal formatting.";
    public static final String ERROR_ILLEGAL_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_NONPOSITIVE_INDEX = "☹ OOPS!!! Index has to be greater than zero.";
    public static final String ERROR_EXCEED_INDEX = "☹ OOPS!!! Currently there are only %d tasks.";
    
}
