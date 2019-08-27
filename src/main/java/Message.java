public class Message {

    public static final String MESSAGE_WELCOME = "Hello! I'm Duke\n%1$sWhat can I do for you?\n%2$s";
    public static final String MESSAGE_ADDED = "Got it. I've added this task:";
    public static final String MESSAGE_DELETED = "Noted. I've removed this task:";
    public static final String MESSAGE_MARK_DONE = "Nice! I've marked this task as done:";
    public static final String MESSAGE_SHOW_TASK_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_SHOW_TASK_SIZE = "Now you have %1$s tasks in the list.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_INVALID_TODO_FORMAT = "The description of a todo cannot be empty.";
    public static final String MESSAGE_INVALID_DEADLINE_FORMAT =
            "Deadlines must have /by and cannot be empty. E.g. deadline return book /by 2/12/2019 1800";
    public static final String MESSAGE_INVALID_EVENT_FORMAT =
            "Events must have /at and cannot be empty. E.g. event project meeting /at 2/12/2019 1800";
    public static final String MESSAGE_INVALID_TASK_INDEX = "Invalid task number. Task numbers follow 1-based indexing.";
    public static final String MESSAGE_ERROR_MISSING_STORAGE_FILE = "%1$sMissing storage file: %2$s";
    public static final String MESSAGE_STORAGE_FILE_CREATED = "%1$sCreated new empty storage file: %2$s";
    public static final String MESSAGE_ERROR_READING_FROM_FILE = "Error when reading file";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date. Follow d/M/yyyy HHmm format. E.g. 2/12/2019 1800";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
}
