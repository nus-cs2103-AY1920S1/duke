package duke.ui;

public class Messages {
    // Add task
    public static final String TASK_ADD_SUCCESS = "Got it. I've added this task:";
    public static final String TASK_ADD_FAILURE = "Fatal: Unable to add task";
    public static final String TASK_MISSING_DESCRIPTION = "The description of a task cannot be empty";
    public static final String DEADLINE_MISSING_TIME = "Deadline cannot be empty";
    public static final String EVENT_MISSING_TIME = "Event time cannot be empty";

    // Mark as done/delete
    public static final String TASK_MARKED_AS_DONE = "Nice! I've marked this task as done:";
    public static final String TASK_ALREADY_DONE = "This task is already done";
    public static final String TASK_DELETED = "Noted. I've removed this task:";
    public static final String MISSING_TASK_NUMBER = "Task number cannot be empty";
    public static final String TASK_DOES_NOT_EXIST = "Task does not exist";

    // Storage
    public static final String LOAD_TASK_FILE_CORRUPTED = "Failed to load tasks from disk. Data file is corrupted.";
    public static final String WRITE_TASK_FAILED = "Failed to update task list on disk. ";

    // List/find tasks
    public static final String LIST_TASKS = "Here are the tasks in your list:";
    public static final String LIST_NO_TASKS = "There are currently no tasks in your list";
    public static final String FIND_TASKS = "Here are the matching tasks in your list:";
    public static final String FIND_NO_TASKS = "There are no matching tasks in your list";
    public static final String FIND_MISSING_QUERY = "Find query cannot be empty";

    // Misc
    public static final String TASKS_COUNT = "Now you have %d tasks in the list";
    public static final String BYE_MESSAGE = "Bye!";
    public static final String UNKNOWN_COMMAND = "Unknown command";

    // This class is for static use only
    private Messages() {
    }
}
