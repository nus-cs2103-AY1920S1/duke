package run;

import task.Task;

import java.util.ArrayList;

/**
 * Handles all the interaction with the user (taking input and printing output).
 */
public class Ui {

    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Prints an error message to the user.
     * @param errorMessage the error message to be printed to the user
     */
    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a message to the user.
     * @param message the message to be printed
     */
    public static void showMessage(String message) {
        System.out.println("---------------------------------------------------------------------");
        System.out.println(message);
        System.out.println("---------------------------------------------------------------------");
    }

    /**
     * Prints exit message (Used when Duke is exited).
     */
    public String exit() {
        return EXIT_MESSAGE;
    }


    /**
     * Prints a list of tasks.
     * @param tasks arraylist of tasks to be printed
     * @return String formatted print of all tasks
     */
    public static String printList(ArrayList<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i - 1);
            stringBuilder.append(i + "." + currTask);
        }
        return stringBuilder.toString();
    }

    /**
     * Prints messages for when task is added to TaskList.
     * @param task the task that was added
     * @param size current number of tasks in TaskList
     * @return String message to be displayed to user after adding task
     */
    public static String printAdd(Task task, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it. I've added this task: ");
        stringBuilder.append("  " + task);
        stringBuilder.append("Now you have " + size + " tasks in the list.");
        return stringBuilder.toString();
    }

    /**
     * Prints message for when a task is marked as done.
     * @param task the task to be marked as done
     * @return String message to be displayed to user after marking task as done
     */
    public static String printDone(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nice! I've marked this task as done: ");
        stringBuilder.append("  " + task);
        return stringBuilder.toString();
    }

    /**
     * Prints messages for when a task is deleted from TaskList.
     * @param task the task to be deleted
     * @param size new total number of tasks in TaskList
     * @return String message to be displayed to user after deleting task
     */
    public static String printDelete(Task task, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Noted. I've removed this task: ");
        stringBuilder.append("  " + task);
        stringBuilder.append("Now you have " + size + " tasks in the list.");
        return stringBuilder.toString();
    }

    /**
     * Prints tasks that were found and special message if not tasks were found to match search string.
     * @param tasks arraylist of found tasks that contained previous search string
     * @return String representation of tasks that were found to meet criteria, otherwise "No tasks found
     *     containing your search" if none found
     */
    public static String printFind(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "No tasks found containing your search!";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i - 1);
            stringBuilder.append(i + "." + currTask);
        }
        return stringBuilder.toString();
    }
}