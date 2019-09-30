package duke.util;

/**
 * Represents different types of events that need to be displayed to the user within the UI.
 */
public enum UiMessage {
    // greetings
    WELCOME("Hello! What can I do for you?"),
    GOODBYE("Bye. Hope to see you again soon!"),
    // storage
    TASKS_IMPORTED("Success! Your tasks have been imported from:"),
    TASKS_SAVED("Success! Your tasks have been saved to:"),
    TASKS_NOT_SAVED("Your task list is empty! Adios :)"),
    TASKS_NOT_FOUND("Existing tasks file not found! Starting duke afresh..."),
    // commands
    TASK_ADDED("Okay! I've added the task."),
    TASK_DONE("Nice! I've marked this task as done:"),
    TASK_SNOOZED("Okay! The task has been snoozed by 1 day."),
    TASK_DELETED("Noted. I've removed this task: "),
    MATCHING_TASKS("Here are the matching tasks in your list..."),
    TASK_TAGGED("Nice! I've added a tag to this task:"),
    TASK_PRIORITISED("Sweet! I've added a priority to this task: "),
    // helpers
    TASKS_STATUS_FRONT("Now you have "),
    TASKS_STATUS_BACK(" items in this list."),
    HINT_LIST("Use list to see all your tasks!");

    private final String message;

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
}
