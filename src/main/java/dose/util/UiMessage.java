package dose.util;

/**
 * Represents different types of events that need to be displayed to the user within the UI.
 */
public enum UiMessage {
    // greetings
    WELCOME("Hello! What can I do for you?"),
    GOODBYE("Bye. Hope to see you again soon!"),
    // storage
    TASKS_IMPORTED("Success! Your tasks have been imported from: "),
    TASKS_SAVED("Success! Your tasks have been saved to: "),
    TASKS_NOT_SAVED("Your task list is empty! Adios :)"),
    TASKS_NOT_FOUND("Existing tasks file not found! Starting dose afresh..."),
    // commands
    TASK_ADDED("Okay! I've added the task."),
    TASK_DONE("Nice! I've marked this task as done: "),
    TASK_SNOOZED("Okay! The task has been snoozed by 1 day."),
    TASK_DELETED("Noted. I've removed this task: "),
    MATCHING_TASKS("Here are the matching tasks in your list..."),
    TASK_TAGGED("Nice! I've added a tag to this task: "),
    TASK_PRIORITISED("Sweet! I've added a priority to this task: "),
    // helpers
    TASK_LIST("Here are the tasks in your list: "),
    TASKS_STATUS_FRONT("Now you have "),
    TASKS_STATUS_BACK(" items in this list."),
    HINT_TODO("Use todo to add a new todo."),
    HINT_DEADLINE("Use deadline /by [deadline] to add a new task with a deadline."),
    HINT_EVENT("Use event /at [time] to add a new event at a time."),
    HINT_DONE("Use done [taskId] to mark a task as done."),
    HINT_DELETE("Use delete [taskId] to remove a task from the list."),
    HINT_SAVE("Use save to save your tasks to disk."),
    HINT_LIST("Use list to see all your tasks!"),
    // todo: add tag, priority, find, snooze
    HELP("Here are the things I can do...");

    private final String message;
    //private ArrayList<UiMessage> helpMessages = new ArrayList<>();

    UiMessage(String message) {
        this.message = message;
    }

    /**
     * Returns a message relating to the event, to be displayed in the UI.
     * @return Message relating to the event, to be displayed in the UI.
     */
    public String getMessage() {
        return this.message;
    }

    public static String getHelpMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(HELP.getMessage()).append("\n");
        sb.append(HINT_TODO.getMessage()).append("\n");
        sb.append(HINT_DEADLINE.getMessage()).append("\n");
        sb.append(HINT_EVENT.getMessage()).append("\n");
        sb.append(HINT_DONE.getMessage()).append("\n");
        sb.append(HINT_DELETE.getMessage()).append("\n");
        sb.append(HINT_SAVE.getMessage()).append("\n");
        sb.append(HINT_LIST.getMessage()).append("\n");
        return sb.toString();
    }
}
