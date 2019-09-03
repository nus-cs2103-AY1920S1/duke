package duke.component;

import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Class for User interaction.
 */
public class Ui {

    /**
     * Displays initial welcome screen of the program.
     */
    public static void showWelcomeScreen() {
        String logo = "____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        displayWithFormat(" " + logo + "\n\t Hello! I'm Duke" + "\n" + "\t What can I do for you?");
    }

    /**
     * Displays (terminating/Good bye)ending screen of the program.
     */
    public static void showGoodByeScreen() {
        displayWithFormat("Bye. Hope to see you again soon!");
    }

    /**
     * Prints acknowledgement of certain task is changed to status of 'completed' to task list.
     * @param task task whose status is being changed.
     */
    public static void printDoneAcknowledgement(Task task) {
        displayWithFormat("Nice! I've marked this task as done:"
                + "\n\t   " + task.toString());
    }

    /**
     * Prints acknowledgement of certain task is added to the task list and
     * number of items in the task list.
     * @param task being added.
     * @param taskCount number of tasks in the task list.
     */
    public static void printAddedAcknowledgement(Task task, int taskCount) {
        displayWithFormat("Got it. I've added this task: "
                + "\n\t   " + task.toString()
                + "\n\t Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints acknowledgement of certain task is deleted from the task list and
     * number of items in the task list.
     * @param task task to be printed.
     * @param taskCount number of tasks in the task list.
     */
    public static void printRemovedAcknowledgement(Task task, int taskCount) {
        displayWithFormat("Noted. I've removed this task:"
                + "\n\t   " + task.toString()
                + "\n\t Now you have "
                + taskCount
                + " tasks in the list.");
    }


    /**
     * Prints message of the certain exceptions.
     * @param e exceptions to be printed.
     */
    public static void printErrorMessage(Exception e) {
        if (e instanceof FileNotFoundException) {
            printErrorWithFormat("Error: Input Text File not Found! Program Exiting...");
        } else if (e instanceof UnsupportedEncodingException) {
            printErrorWithFormat("Error: Unable to write to file! Program Exiting...");
        } else {
            printErrorWithFormat(e.toString());
        }
    }


    /**
     * Prints a certain message with Duke format.
     * @param message message to be printed.
     */
    public static void displayWithFormat(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t " + message);
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    /**
     * Prints a certain error message with Duke format.
     * @param errorMessage error message to be printed.
     */
    public static void printErrorWithFormat(String errorMessage) {
        System.err.println("\t____________________________________________________________");
        System.err.println("\t " + errorMessage);
        System.err.println("\t____________________________________________________________");
        System.err.println();
    }

    /**
     * Prints a certain task list with Duke format.
     * @param taskList to be printed on display.
     */
    public static void printTaskList(TaskList taskList) {
        System.out.print("\t____________________________________________________________"
                + "\n\t Here are the tasks in your list:");
        System.out.println(taskList.toString());
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    /**
     * Printst a certain task list with match cases in Duke format.
     * @param taskList to be printed on display.
     */
    public static void printFoundTaskList(TaskList taskList) {
        System.out.print("\t____________________________________________________________"
                + "\n\t Here are the matching tasks in your list:");
        System.out.println(taskList.toString());
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }
}