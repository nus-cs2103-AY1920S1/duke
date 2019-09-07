package duke.ui;

import java.lang.StringBuilder;
import java.util.Scanner;

/**
 * This is the user interface of the Duke program. The Duke program will print information here. The user interface can
 * display the change information, error messages, exit message, list of task, welcome message to the user.
 */
public class Ui implements UserInterface {

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
    public String showWelcome() {
        StringBuilder outputBuilder = new StringBuilder();
        String logo = " ____        _        \n"
                      + "|  _ \\ _   _| | _____ \n"
                      + "| | | | | | | |/ / _ \\\n"
                      + "| |_| | |_| |   <  __/\n"
                      + "|____/ \\__,_|_|\\_\\___|\n";
        outputBuilder.append("Hello from\n" + logo);
        outputBuilder.append("    Hello! I'm Duke\n");
        outputBuilder.append("    What can I do for you?\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        System.out.print(output);
        return output;
    }

    /** Prints a error message for the user when an error has occurred.
     * @param message the error message for the error
     */
    public String showError(String message) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    🙁 OOPS!! " + message + " 🙁\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        System.out.print(output);
        return output;
    }

    /**
     * Prints a loading error message for the user. This will occur when Duke is unable to load the
     * file path specified in Duke. See {@link duke.main.Duke} for more information.
     */
    public String showLoadingError() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("* FAILED TO LOAD DATA\n");
        String output = outputBuilder.toString();
        System.out.print(output);
        return output;
    }

    /**
     * Prints a error message for the user with the constituent line number and the its content.
     * @param lineCount the line number of the error
     * @param line the contents of the line itself
     */
    public String showLineError(int lineCount, String line) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("* Unable to parse line " + lineCount + " : " + line + "\n");
        String output = outputBuilder.toString();
        System.out.print(output);
        return output;
    }

    /**
     * Reads the command entered by the user.
     * @return Returns the command entered by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    public String readCommand(String userInput) {
        return userInput;
    }

    /**
     * Prints the current tasks in the list.
     * @param list the list of tasks to be printed
     */
    public String showTable(String list) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ============================================================\n");
        outputBuilder.append("    Here are the tasks in your list:\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append(list);
        outputBuilder.append("    ============================================================\n");
        String output = outputBuilder.toString();
        System.out.print(output);
        return output;
    }

    /**
     * Prints the results of tasks found in the list.
     * @param list the list of tasks to be printed
     */
    public String showResultsFound(String list) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ============================================================\n");
        outputBuilder.append("    Here are the matching tasks in your list:\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append(list);
        outputBuilder.append("    ============================================================\n");
        String output = outputBuilder.toString();
        System.out.print(output);
        return output;
    }

    /**
     * Prints a message that informs the user that a task has been added into the list.
     * @param task the task to be added into the list
     * @param size the number of tasks in the list
     */
    public String showAddInformation(String task, int size) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("     ------------------------------------------------------------\n");
        outputBuilder.append("     Got it. I've added this task:\n");
        outputBuilder.append("     ➕  " + task + "\n");
        outputBuilder.append("     Now you have " + size + " tasks in the list.\n");
        outputBuilder.append("     ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        System.out.print(output);
        return output;
    }

    /**
     * Prints a message that informs the user that the task has been marked as done.
     * @param task the task that is marked done
     */
    public String showMarkedAsDone(String task) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    Nice! I've marked this task as done:\n");
        outputBuilder.append("      " + task + "\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        System.out.print(output);
        return output;
    }

    /**
     * Prints a message that informs the user that the task has been deleted.
     * @param task the task that is deleted
     * @param size the number of tasks remaining in the list
     */
    public String showDeletedMessage(String task, int size) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    Noted. I've removed this task:\n");
        outputBuilder.append("    🗑  " + task + "\n");
        outputBuilder.append("    Now you have " + size + " tasks in the list.\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        System.out.print(output);
        return output;
    }

    /**
     * Prints a exit message for the user.
     */
    public String showExitMessage() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    Bye. Hope to see you again soon!\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        System.out.print(output);
        return output;
    }

}
