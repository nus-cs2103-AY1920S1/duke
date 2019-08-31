package duke.ui;

import java.util.Scanner;

/**
 * This is the user interface of the Duke program. The Duke program will print information here. The user interface can
 * display the change information, error messages, exit message, list of task, welcome message to the user.
 */
public class Ui {

    /**
     * This is the Scanner object used to read user input.
     */
    private Scanner sc;

    /**
     * Constructs a new user interface to display information.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a welcome message for the user.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                      + "|  _ \\ _   _| | _____ \n"
                      + "| | | | | | | |/ / _ \\\n"
                      + "| |_| | |_| |   <  __/\n"
                      + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ------------------------------------------------------------");
    }

    /** Prints a error message for the user when an error has occurred.
     * @param message the error message for the error
     */
    public void showError(String message) {
        String output = "    ------------------------------------------------------------\n"
                        + "    🙁 OOPS!! " + message + " 🙁\n"
                        + "    ------------------------------------------------------------";
        System.out.println(output);
    }

    /**
     * Prints a loading error message for the user. This will occur when Duke is unable to load the
     * file path specified in Duke. See {@link duke.main.Duke} for more information.
     */
    public void showLoadingError() {
        System.out.println("* FAILED TO LOAD DATA");
    }

    /**
     * Prints a error message for the user with the constituent line number and the its content.
     * @param lineCount the line number of the error
     * @param line the contents of the line itself
     */
    public void showLineError(int lineCount, String line) {
        System.out.println("* Unable to parse line " + lineCount + " : " + line);
    }

    /**
     * Reads the command entered by the user.
     * @return Returns the command entered by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the current tasks in the list.
     * @param list the list of tasks to be printed
     */
    public void showTable(String list) {
        System.out.println("    ============================================================");
        System.out.println("    Here are the tasks in your list:");
        System.out.println("    ------------------------------------------------------------");
        System.out.print(list);
        System.out.println("    ============================================================");
    }

    /**
     * Prints the results of tasks found in the list.
     * @param list the list of tasks to be printed
     */
    public void showResultsFound(String list) {
        System.out.println("    ============================================================");
        System.out.println("    Here are the matching tasks in your list:");
        System.out.println("    ------------------------------------------------------------");
        System.out.print(list);
        System.out.println("    ============================================================");
    }

    /**
     * Prints a message that informs the user that a task has been added into the list.
     * @param task the task to be added into the list
     * @param size the number of tasks in the list
     */
    public void showAddInformation(String task, int size) {
        System.out.println("     ------------------------------------------------------------");
        System.out.println("     Got it. I've added this task:");
        System.out.println("     ➕  " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        System.out.println("     ------------------------------------------------------------");
    }

    /**
     * Prints a message that informs the user that the task has been marked as done.
     * @param task the task that is marked done
     */
    public void showMarkedAsDone(String task) {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println("    ------------------------------------------------------------");
    }

    /**
     * Prints a message that informs the user that the task has been deleted.
     * @param task the task that is deleted
     * @param size the number of tasks remaining in the list
     */
    public void showDeletedMessage(String task, int size) {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    🗑  " + task);
        System.out.println("    Now you have " + size + " tasks in the list.");
        System.out.println("    ------------------------------------------------------------");
    }

    /**
     * Prints a exit message for the user.
     */
    public void showExitMessage() {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ------------------------------------------------------------");
    }

}
