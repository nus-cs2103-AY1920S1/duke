package command;

public class Ui {

    private static final String INTRODUCTION = "     Hello! I'm Duke";
    private static final String USER_PROMPT = "     What can I do for you?";
    private static final String SEPARATOR = "    ____________________________________________________________";
    private static final String CREATE_FILE = "     Creating new file.";
    private static final String SHOW_LIST = "     Here are the tasks in your list:";
    private static final String INVALID_LIST_ENTRY = "     List entry does not exist!";
    private static final String MATCHING_LIST = "     Here are the matching tasks in your list:";
    private static final String NO_MATCH = "     There is no match in your list!";
    private static final String LOAD_ERROR = "     Error loading file.";
    private static final String SAVE_ERROR = "     Error saving file.";
    private static final String MARK_TASK_COMPLETE = "     Nice! I've marked this task as done: ";
    private static final String ADD_TASK = "     Got it. I've added this task:";
    private static final String REMOVE_TASK = "     Noted. I've removed this task: ";
    private static final String CLOSING_STATEMENT = "     Bye. Hope to see you again soon!";
    private static final String INVALID_COMMAND = "    " + "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(";

    public Ui() {
    }

    /**
     * Opening statement, introduction of Duke.
     * Includes a user prompt for input
     */
    public void openingStatement() {
        System.out.println(SEPARATOR);
        System.out.println(INTRODUCTION);
        System.out.println(USER_PROMPT);
        System.out.println(SEPARATOR);
        System.out.println("");
    }

    /**
     * Closing statement.
     */
    public void closingStatement() {
        System.out.println(SEPARATOR);
        System.out.println(CLOSING_STATEMENT);
        System.out.println(SEPARATOR);
    }

    /**
     * Invalid entry of the list.
     * When user tries to delete/mark done an entry
     * that does not exist.
     */
    public void invalidEntry() {
        System.out.println(INVALID_LIST_ENTRY);
    }

    /**
     * Create a .txt file as list.
     * Happens when existing .txt file does not exist.
     */
    public void createFile() {
        System.out.println(SEPARATOR);
        System.out.println(CREATE_FILE);
        System.out.println(SEPARATOR);
    }

    /**
     * Load error occurs when file does not exist.
     */
    public void loadError() {
        System.out.println(SEPARATOR);
        System.out.println(LOAD_ERROR);
        System.out.println(SEPARATOR);
    }

    /**
     * Save error occurs when filepath is invalid.
     */
    public void saveError() {
        System.out.println(SEPARATOR);
        System.out.println(SAVE_ERROR);
        System.out.println(SEPARATOR);
    }

    /**
     * Mark a specific task as done.
     */
    public void completedTask() {
        System.out.println(MARK_TASK_COMPLETE);
        System.out.print("       "); //indentation
    }

    public void addTask() {
        System.out.println(ADD_TASK);
        System.out.print("       "); // indentation
    }

    public void deleteTask() {
        System.out.println(REMOVE_TASK);
        System.out.print("       "); //indentation
    }

    public void showList() {
        System.out.println(SHOW_LIST);
    }

    public void separator() {
        System.out.println(SEPARATOR);
    }

    public void matchingList() {
        System.out.println(MATCHING_LIST);
    }

    public void noMatch() {
        System.out.println(NO_MATCH);
    }

    public void numTasks(int n) {
        System.out.println("     Now you have " + (n) + " tasks in the list.");
    }

    public String invalidCommand() {
        return INVALID_COMMAND;
    }
}
