package bot.duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import bot.duke.task.Task;

/**
 * Contains methods that interfaces with the user.
 */
public class Ui {

    static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static final String WELCOME_MSG = "Hello! I'm Duke\n"
            + "What can I do for you?";
    static final String EXIT_MSG = "Bye. Hope to see you again soon!\n"
            + "Press ENTER to exit!";

    static final Scanner SCANNER = new Scanner(System.in);

    static String preLaunchMsg = "";

    public static void addPreLaunchMsg(String msg) {
        preLaunchMsg = preLaunchMsg + msg;
    }
    /**
     * Prints the Welcome message.
     */
    public static void printWelcomeMsg() {
        final int WELCOME_PREPEND_SPACING_COUNT = 23;
        CmdUx.printHBars(preLaunchMsg);
        CmdUx.print("\n".repeat(WELCOME_PREPEND_SPACING_COUNT));
        CmdUx.print("\nHello from\n" + LOGO);
        CmdUx.printHBars(WELCOME_MSG);
    }

    /**
     * Prints the Exit message.
     */
    public static void printExitMsg() {
        CmdUx.printHBars(EXIT_MSG);
    }

    /**
     * Reads input from the console.
     *
     * @return input from the console
     */
    public static String readInput() {
        return SCANNER.nextLine();
    }

    /**
     * Lists the Task objects in a given list of Task objects.
     *
     * @param taskList An ArrayList of tasks
     */
    public static void listTasks(ArrayList<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("Here are the tasks in your list:\n");
        for (Task task : taskList) {
            sb.append(i++ + "." + task.toString() + "\n");
        }
        CmdUx.printHBars(sb.toString());
    }

    /**
     * Lists search results.
     *
     * @param tasks         Full list of tasks
     * @param searchResults Index of tasks in search result
     */
    public static void listSearchResults(ArrayList<Task> tasks, ArrayList<Integer> searchResults) {
        StringBuilder sb = new StringBuilder();
        if (searchResults.size() > 0) {
            sb.append("Here are the matching tasks in your list:\n");
            for (Integer index : searchResults) {
                sb.append(index + "." + tasks.get(index).toString() + "\n");
            }
        } else {
            sb.append("There are no matching tasks in your list! X_X \n"
                    + "Try again with another search term!");
        }

        CmdUx.printHBars(sb.toString());
    }

    /**
     * Prints the message upon successfully adding a task to a given list of Task objects.
     *
     * @param tasks Given ArrayList of Task objects, for counting purposes only
     * @param task  The Task that was added
     */
    public static void printAddSuccess(ArrayList<Task> tasks, Task task) {
        CmdUx.printHBars("Got it. I've added this task: \n"
                + "  " + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the message upon successfully marking a Task as done.
     *
     * @param chosenTask The Task that was made done
     */
    public static void printDoneSuccess(Task chosenTask) {
        CmdUx.printHBars("Nice! I've marked this task as done: \n"
                + " " + Checkbox.TICK.icon + " " + chosenTask.getTaskName());
    }

    /**
     * Prints the message upon successfully deleting a Task from a given list of Task objects.
     *
     * @param tasks       Given ArrayList of Task objects, for counting purposes only
     * @param deletedTask The Task that was deleted
     */
    public static void printDeleteSuccess(ArrayList<Task> tasks, Task deletedTask) {
        CmdUx.printHBars("Noted. I've removed this task: \n"
                + "  " + deletedTask.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints a given error message.
     *
     * @param errorMessage Given error message
     */
    public static void exposeError(String errorMessage) {
        CmdUx.printHBars(errorMessage);
    }


}
