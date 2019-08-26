package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Create a UI class to manage UI for users.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    private static final String LINES = "____________________________________________________________\n";

    public Ui() {
    }

    /**
     * Prompts user for an input.
     *
     * @return A new line.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints loading Error.
     */
    public void showLoadingError() {
        System.out.println("Cannot load previous task");
    }

    /**
     * Prints horizontal lines.
     */
    public void showLine() {
        System.out.print(LINES);
    }

    /**
     * Prints error message.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints the UI for ending the programme.
     *
     * @param tasks TaskList object.
     */
    public void showExitMessage(ArrayList<Task> tasks) {
        System.out.println("Saving your current tasks : ");
        for (Task t : tasks) {
            System.out.println(t);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a welcome message for users.
     *
     * @param tasks TaskList object.
     */
    public void showWelcome(ArrayList<Task> tasks) {
        System.out.println(display("Hello! I'm Duke\nFeed me some commands!\n"));
        showLine();
        System.out.println("Leftover tasks from before : ");
        for (Task t : tasks) {
            System.out.println(t);
        }
        showLine();
    }

    /**
     * Prints the UI for adding a task.
     *
     * @param t Task that is added.
     * @param n Number of tasks in the TaskList.
     */
    public void showAddedTask(Task t, int n) {
        System.out.println("Got it. I've added this task:\n  "
                + t
                + "\nNow you have " + n + " tasks in the list.");
    }

    /**
     * Prints the UI for deleting a task.
     *
     * @param t Task that is deleted.
     * @param n The index of the task that is deleted in TaskList.
     */
    public void showDeletedTask(Task t, int n) {
        System.out.println("Noted. I've removed this task:\n  "
                + t
                + "\nNow you have " + n + " tasks in the list.");
    }

    /**
     * Prints the UI for completing a task.
     *
     * @param t Task that is completed.
     */
    public void showDoneTask(Task t) {
        System.out.println("Nice! I've marked this task as done:\n"
                + t);
    }

    /**
     * Prints the tasks within TaskList.
     *
     * @param list TaskList object.
     */
    public void printList(TaskList list) {
        try {
            System.out.println("Here are the task in your list:");
            for (int i = 0; i < list.tasks.size(); i++) {
                System.out.println(i + 1 + "." + list.tasks.get(i));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.print("Your list is empty.");
        }
    }

    private String display(String text) {
        return LINES + text + LINES;
    }
}
