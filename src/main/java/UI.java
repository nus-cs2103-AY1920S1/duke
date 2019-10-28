import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles the user interface that the user interact with.
 */
public class UI {

    public static final String divider = "\t____________________________________________________________";
    public static final String messageFrontInden = "\t  ";

    private Scanner sc;

    /**
     * Construct a user interface object for the user to interact.
     */
    public UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Scans for the next line of user input.
     *
     * @return User input.
     */
    public String readCommand() {
        String inputStr = sc.nextLine();

        return inputStr;
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        this.showLine();
        String logo = "\t   ____        _        \n"
                + "\t  |  _ \\ _   _| | _____ \n"
                + "\t  | | | | | | | |/ / _ \\\n"
                + "\t  | |_| | |_| |   <  __/\n"
                + "\t  |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printWithInden("Hello! I'm Duke");
        printWithInden("What can I do for you?");
        this.showLine();
    }

    public void showLine() {
        System.out.println(divider);
    }

    public void showLoadingError() {
        System.out.println("Unable to load previous data! A new task list is created.");
    }

    public void showLoadingSuccessful() {
        System.out.println("Previous task list loaded successfully.");
    }

    public void showError(String message) {
        printWithInden(message);
    }

    public void printWithInden(String message) {
        System.out.println(messageFrontInden + message);
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks List of tasks.
     */
    public void showList(TaskList tasks) {
        printWithInden("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            printWithInden((i + 1) + ". " + tasks.getTask(i));
        }
    }

    /**
     * Filters and displays the list of tasks based on keyword.
     *
     * @param tasks List of tasks.
     * @param keyword Word that the user wants to find.
     */
    public void showListWithKeyword(TaskList tasks, String keyword) {
        printWithInden("Here are the matching tasks in your list:");
        String word = keyword.trim();
        int count = 1;
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task task = tasks.getTask(i);
            String desc = task.getDescription();
            if (desc.contains(word)) {
                printWithInden((count) + ". " + tasks.getTask(i));
                count++;
            } else {
                continue;
            }
        }
    }

    public void showExitMessage() {
        printWithInden("Bye. Hope to see you again soon!");
    }

    public void showDoneMessage(Task doneTask) {
        printWithInden("Nice! I've marked this task as done:");
        printWithInden("  " + doneTask);
    }

    /**
     * Displays the result after deletion of task.
     *
     * @param deletedTask Task that is deleted from the task list.
     * @param tasks List of tasks.
     */
    public void showDeleteMessage(Task deletedTask, TaskList tasks) {
        printWithInden("Noted. I've removed this task:");
        printWithInden("  " + deletedTask);
        printWithInden("Now you have " + tasks.getTaskCount() + " tasks in the list.");
    }

    /**
     * Displays the result after adding a task.
     *
     * @param addedTask Task that is added into the task list.
     * @param tasks List of tasks.
     */
    public void showAddMessage(Task addedTask, TaskList tasks) {
        printWithInden("Got it. I've added this task:");
        printWithInden("  " + addedTask);
        printWithInden("Now you have " + tasks.getTaskCount() + " tasks in the list.");
    }
}
