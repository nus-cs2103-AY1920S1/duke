package jermi.misc;

/**
 * Constants used in the application.
 */
public class Constant {
    public static final String COMMAND_ADD_ACTIVITY_DATE_TIME_DELIMITER = "/";
    public static final String COMMAND_ADD_MESSAGE = "Got it. I've added this task:";
    public static final String COMMAND_ADD_PREPOSITION_DATE_TIME_DELIMITER = " ";
    public static final String COMMAND_CLEAR_MESSAGE = "Noted. I've removed all the tasks in the list.";
    public static final String COMMAND_DELETE_MESSAGE = "Noted. I've removed this task:";
    public static final String COMMAND_DONE_MESSAGE = "Nice! I've marked this task as done:";
    public static final String COMMAND_FIND_KEYWORDS_DELIMITER = " ";
    public static final String COMMAND_FIND_MESSAGE = "Here are the matching tasks in your list:";
    public static final String COMMAND_LIST_MESSAGE = "Here are the tasks in your list:";
    public static final String COMMAND_NUMBER_OF_TASKS = "Now you have %d task%s in the list.";
    public static final String COMMAND_PLURAL_NOUN = "s";
    public static final String COMMAND_SINGULAR_NOUN = "";
    public static final String COMMAND_TASK_FORMAT = "  %s";
    public static final String COMMAND_TASK_FORMAT_WITH_INDEX = "%d.%s";
    public static final String COMMAND_HELP_MESSAGE = "Here is the list of commands:";
    public static final String DATA_PATH = "data/jermi.txt";
    public static final String DEADLINE_STRING_REPRESENTATION = "%s (by: %s)";
    public static final String DEADLINE_TYPE_CODE = "D";
    public static final String DIALOG_BOX_FXML_PATH = "/view/DialogBox.fxml";
    public static final String EVENT_STRING_REPRESENTATION = "%s (at: %s)";
    public static final String EVENT_TYPE_CODE = "E";
    public static final String EXCEPTION_CORRUPTED_SAVE_FORMAT_MESSAGE = "Save format '%s' is corrupted.";
    public static final String EXCEPTION_EMPTY_DESCRIPTION_MESSAGE = "The description of %s cannot be empty.";
    public static final String EXCEPTION_ERROR_MESSAGE = "☹ OOPS! %s";
    public static final String EXCEPTION_INVALID_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means :-(";
    public static final String EXCEPTION_INVALID_INDEX_MESSAGE = "The task index is invalid!";
    public static final String EXCEPTION_LOADING_MESSAGE = "An error has occurred while loading: %s";
    public static final String EXCEPTION_SAVING_MESSAGE = "An error has occurred while saving: %s";
    public static final String FORMATTER_BORDER = "* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n";
    public static final String FORMATTER_COMMAND_DETAILS_DELIMITER = " ";
    public static final String FORMATTER_DEFAULT_DETAILS = "";
    public static final String FORMATTER_EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String FORMATTER_MESSAGE_FORMAT = "  %s\n";
    public static final String FORMATTER_WELCOME_MESSAGE_LINE_1 = "Hello! I'm Jermi";
    public static final String FORMATTER_WELCOME_MESSAGE_LINE_2 = "What can I do for you?";
    public static final String FORMATTER_ADD_BLANK_LINE = "";
    public static final String FORMATTER_WELCOME_MESSAGE_HELP = "Type 'help' to view the list of commands.";
    public static final String JERMI_IMAGE_PATH = "/images/Jermi.png";
    public static final String MAIN_WINDOW_FXML_PATH = "/view/MainWindow.fxml";
    public static final String CLEAR_COMMAND = "clear";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String DELETE_COMMAND = "delete";
    public static final String DONE_COMMAND = "done";
    public static final String EVENT_COMMAND = "event";
    public static final String EXIT_COMMAND = "bye";
    public static final String FIND_COMMAND = "find";
    public static final String LIST_COMMAND = "list";
    public static final String TODO_COMMAND = "todo";
    public static final String HELP_COMMAND = "help";
    public static final String STORAGE_SAVE_FORMAT_DELIMITER = "\\|";
    public static final String TASK_DONE_ICON = "✔";
    public static final String TASK_DONE_STRING_REPRESENTATION = "1";
    public static final String TASK_INPUT_DATE_TIME_FORMAT = "d/M/yyyy HHmm";
    public static final String TASK_OUTPUT_DATE_TIME_FORMAT = "d MMMM yyyy, h.mma";
    public static final String TASK_SAVE_FORMAT = "%s|%s|%s";
    public static final String TASK_SAVE_FORMAT_DATE_TIME_EXTENSION = "%s|%s";
    public static final String TASK_STRING_REPRESENTATION = "[%s][%s] %s";
    public static final String TASK_UNDONE_ICON = "✘";
    public static final String TASK_UNDONE_STRING_REPRESENTATION = "0";
    public static final String TO_DO_TYPE_CODE = "T";
    public static final String USER_IMAGE_PATH = "/images/User.png";
    public static final String HELP_MESSAGE_LIST = "◇ list : View the list of tasks";
    public static final String HELP_MESSAGE_DEADLINE = "◇ deadline <description> /by <deadline> : "
            + "Add deadline to the list";
    public static final String HELP_MESSAGE_EVENT = "◇ event <description> /at <date and time> : "
            + "Add event to the list";
    public static final String HELP_MESSAGE_TODO = "◇ todo <description> : Add todo to the list";
    public static final String HELP_MESSAGE_DONE = "◇ done <task index> : Mark the task as done";
    public static final String HELP_MESSAGE_DELETE = "◇ delete <task index> : Delete the task from the list";
    public static final String HELP_MESSAGE_FIND = "◇ find <keyword 1> <other keywords>... : "
            + "Find all tasks that partially/exactly match any of the keywords";
    public static final String HELP_MESSAGE_CLEAR = "◇ clear : Delete all tasks from the list";
    public static final String HELP_MESSAGE_EXIT = "◇ bye : Exit the program";

    public static final int COMMAND_ADD_ACTIVITY_DATE_TIME_SPLIT_LIMIT = 2;
    public static final int COMMAND_ADD_ACTIVITY_INDEX = 0;
    public static final int COMMAND_ADD_DATE_TIME_INDEX = 1;
    public static final int COMMAND_ADD_PREPOSITION_DATE_TIME_INDEX = 1;
    public static final int COMMAND_ADD_PREPOSITION_DATE_TIME_SPLIT_LIMIT = 2;
    public static final int DUMMY_ARRAY_SIZE = 0;
    public static final int FORMATTER_COMMAND_DETAILS_SPLIT_LIMIT = 2;
    public static final int FORMATTER_COMMAND_INDEX = 0;
    public static final int FORMATTER_DETAILS_INDEX = 1;
    public static final int INSERT_MESSAGE_INDEX = 0;
    public static final int SHOULD_EXIT_INDEX = 0;
    public static final int STORAGE_DATE_TIME_INDEX = 3;
    public static final int STORAGE_DESCRIPTION_INDEX = 2;
    public static final int STORAGE_IS_DONE_INDEX = 1;
    public static final int STORAGE_TYPE_CODE_INDEX = 0;
    public static final int TASK_LIST_START_INDEX = 1;
}
