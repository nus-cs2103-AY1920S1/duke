import java.util.Scanner;

/**
 * Contains responses and interactions with the user.
 */
public class Ui {

    private static final String INDENT = "     ";

    /**
     * Displays the Duke logo.
     */
    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.printf("Hello from\n" + logo);
    }

    /**
     * Displays a long line.
     */
    public void showLine() {
        System.out.printf("    ____________________________________________________________\n");
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.printf(INDENT + "Hello! I'm Duke\n" + INDENT + "What can I do for you?\n");
        showLine();
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbye() {
        System.out.printf(INDENT + "Bye. Hope to see you again soon!\n");
    }

    public void sayHi() {
        System.out.printf(INDENT + "Hi :-D Say Something to me!\n");
    }

    /**
     * Displays a finished task.
     *
     * @param doneTask Task done.
     */
    public void showDone(String doneTask) {
        System.out.printf(INDENT + "Nice! I've marked this task as done: \n" + INDENT  + " " + doneTask + "\n");
    }

    /**
     * Displays a newly added task.
     *
     * @param task Task added.
     */
    public void showAdd(String task) {
        System.out.printf(INDENT + "Got it. I've added this task: \n" + INDENT + " " + task + "\n");
    }

    /**
     * Displays a header for the task list contends.
     */
    public void showListHeader() {
        System.out.printf(INDENT + "Here are the tasks in your list:\n");
    }

    /**
     * Displays a header for the task search.
     */
    public void showSearchHeader() {
        System.out.printf(INDENT + "Here are the matching tasks in your list:\n");
    }

    /**
     * Displays a task removed from the task list.
     *
     * @param removed Task removed.
     */
    public void showRemove(String removed) {
        System.out.printf(INDENT + "Noted. I've removed this task:\n" + INDENT + " " + removed + "\n");
    }

    /**
     * Displays a no result found message for search.
     */
    public void showNotFound() {
        System.out.printf(INDENT + "Hummm, nothing has been found.\n");
    }

    public void showError(String message) {
        System.out.printf(INDENT + message + "\n");
    }

    /**
     * Displays and error message when the file is unable to be loaded.
     */
    public void showLoadingError() {
        System.out.printf(INDENT + "oops, something went wrong\n");
    }

    /**
     * Displays the number of tasks in the task list.
     *
     * @param numOfTask Number of tasks.
     */
    public void showCount(int numOfTask) {
        if (numOfTask < 2) {
            System.out.printf(INDENT + "Now you have %d task in the list.\n", numOfTask);
        } else {
            System.out.printf(INDENT + "Now you have %d tasks in the list.\n", numOfTask);
        }
    }

    /**
     * Displays a header for the clear command.
     */
    public void showClearList() {
        System.out.printf(INDENT + "Great! I've removed all your tasks!\n");
    }

    /**
     * Displays the task.
     *
     * @param index Index of the task in the task list.
     * @param task Task to be displayed.
     */
    public void showTask(int index, String task) {
        System.out.printf(INDENT + " " + index + ". " + task + "\n");
    }

    /**
     * Reads the user input String from the keyboard.
     *
     * @return User input String.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}

