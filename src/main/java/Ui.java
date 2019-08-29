/**
 * Contains responses and interactions with the user.
 */
public class Ui {

    private static final String LINE = "    ____________________________________________________________\n";
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
        System.out.printf(LINE);
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.printf(LINE + INDENT + "Hello! I'm Duke\n" + INDENT + "What can I do for you?\n"
                + LINE);
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbye() {
        System.out.printf(LINE + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
    }

    /**
     * Displays a finished task.
     *
     * @param doneTask Task done.
     */
    public void showDone(String doneTask) {
        System.out.printf(LINE + INDENT + "Nice! I've marked this task as done: \n" + INDENT +
                doneTask + "\n" + LINE);
    }

    /**
     * Displays a newly added task.
     *
     * @param task Task added.
     */
    public void showAdd(String task) {
        System.out.printf(LINE + INDENT + "Got it. I've added this task: \n" + INDENT + " " + task + "\n");
    }

    /**
     * Displays a header for the task list contends.
     */
    public void showListHeader() {
        System.out.printf(LINE + INDENT + "Here are the tasks in your list:\n");
    }

    /**
     * Displays a task removed from the task list.
     *
     * @param removed Task removed.
     */
    public void showRemove(String removed) {
        System.out.printf(LINE + INDENT + "Noted. I've removed this task:\n" + INDENT + " " + removed + "\n");
    }

    /**
     * Displays the error message for unknown user command.
     */
    public void showUnknownError() {
        System.out.printf(LINE + INDENT + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
    }

    /**
     * Displays an error message for the app's customized exceptions.
     *
     * @param e Exception to be printed.
     */
    public void printDukeException(DukeException e) {
        System.out.printf(LINE + INDENT + e.toString() + "\n"  + LINE);
    }

    /**
     * Displays an error message when the task list file is not found.
     */
    public void printFileNotFoundException() {
        System.out.printf(LINE + INDENT + "oops, task list is not found :o\n" + LINE);
    }

    /**
     * Displays the error message when file read and write goes wrong.
     */
    public void printIOException() {
        System.out.printf(LINE + INDENT + "oops, something went wrong :(\n" + LINE);
    }

    /**
     * Displays the error message when user enters an none-integer for the task index.
     */
    public void printTaskIndexMismatchException() {
        System.out.printf(LINE + INDENT +
                "☹ OOPS!!! I cannot recognise that task index. :-(" + "\n" + LINE);
    }

    /**
     * Displays and error message when the file is unable to be loaded.
     */
    public void showLoadingError() {
        System.out.printf(LINE + INDENT + "oops, something went wrong\n" + LINE);
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
     * Displays the task.
     *
     * @param index Index of the task in the task list.
     * @param task Task to be displayed.
     */
    public void showTask(int index, String task) {
        System.out.printf(INDENT + " " + index + ". " + task + "\n");
    }
}
