package duke.util;

public enum UiMessage {
    // greetings
    WELCOME("Hello! What can I do for you?"),
    GOODBYE("Bye. Hope to see you again soon!"),
    // storage
    TASKS_IMPORTED("Success! Your tasks have been imported from: "),
    TASKS_SAVED("Success! Your tasks have been saved to: "),
    TASKS_NOT_SAVED("Your task list is empty! Adios :)"),
    TASKS_NOT_FOUND("Existing tasks file not found! Starting duke afresh..."),
    // miscellaneous
    LINE("____________________________________________________________");

    private final String message;

    UiMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
