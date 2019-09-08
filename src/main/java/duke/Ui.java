package duke;

import java.util.Scanner;

public class Ui {

    private static final         String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String INTRODUCTION = "Hello! I'm Duke";
    private static final String USER_PROMPT = "What can I do for you?";
    private static final String SEPARATOR = "    ____________________________________________________________";
    private static final String CREATE_FILE = "Creating new file.";
    private static final String SHOW_LIST = "Here are the tasks in your list:";
    private static final String INVALID_LIST_ENTRY = "List entry does not exist!";
    private static final String MATCHING_LIST = "Here are the matching tasks in your list:";
    private static final String NO_MATCH = "There is no match in your list!";
    private static final String LOAD_ERROR = "Error loading file.";
    private static final String SAVE_ERROR = "Error saving file.";
    private static final String MARK_TASK_COMPLETE = "Nice! I've marked this task as done: ";
    private static final String ADD_TASK = "Got it. I've added this task:";
    private static final String REMOVE_TASK = "Noted. I've removed this task: ";
    private static final String CLOSING_STATEMENT = "Bye. Hope to see you again soon!";
    private static final String INVALID_COMMAND = "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(";

    public Ui() {
    }

    public String welcomeStatement() {
        return "Hello from\n" + LOGO;
    }

    /**
     * Opening statement, introduction of Duke.
     * Includes a user prompt for input
     */
    public String openingStatement() {
        return INTRODUCTION + "\n" + USER_PROMPT + "\n";
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        return input;
    }
    /**
     * Closing statement.
     */
    public String closingStatement() {
        return CLOSING_STATEMENT;
    }

    /**
     * Invalid entry of the list.
     * When user tries to delete/mark done an entry
     * that does not exist.
     */
    public String invalidEntry() {
        return INVALID_LIST_ENTRY;
    }

    /**
     * Create a .txt file as list.
     * Happens when existing .txt file does not exist.
     */
    public String createFile() {
        return CREATE_FILE;
    }

    /**
     * Load error occurs when file does not exist.
     */
    public String loadError() {
        return LOAD_ERROR;
    }

    /**
     * Save error occurs when filepath is invalid.
     */
    public String saveError() {
        return SAVE_ERROR;
    }

    /**
     * Mark a specific task as done.
     */
    public String completedTask() {
        return MARK_TASK_COMPLETE;
    }

    public String addTask() {
        return ADD_TASK;
    }

    public String deleteTask() {
        return REMOVE_TASK;
    }

    public String showList() {
        return SHOW_LIST;
    }

    public void separator() {
        System.out.println(SEPARATOR);
    }

    public String matchingList() {
        return MATCHING_LIST;
    }

    public String noMatch() {
        return NO_MATCH;
    }

    public String numTasks(int n) {
        return "Now you have " + (n) + " tasks in the list.";
    }

    public String invalidCommand() {
        return INVALID_COMMAND;
    }

    public void nextCommand() {
        System.out.println(SEPARATOR);
        System.out.println();
    }
}
