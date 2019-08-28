package duke.core;

import duke.task.Task;
import java.util.Scanner;

/**
 * Represents a system that deals with user interaction, for example, printing 
 * welcome messages and prmopts.
 */
public class Ui {
    /**
     * A <code>Scanner</code> used to read user input.
     */
    private Scanner sc;

    /**
     * Constructs a <code>Ui</code> object and initilizes the 
     * <code>Scanner</code> to read user input from the system.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Shows Duke logo and welcome message, and prompts user to type instructions.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Shows an error in loading the file where past tasks are stored.
     */
    public void showLoadingError() {
        System.out.println("Failed to Load past tasks :-(");
    }

    /**
     * Reads one line of user instruction.
     * @return A string that represents the user instruction.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows a divider line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows that a <code>Task</code> has been added, and displays the number 
     * of current tasks in the list.
     * @param t The <code>Task</code> that is added to the list.
     * @param size The number of tasks stored in the <code>TaskList</code>.
     */
    public void addedTask(Task t, int size) {
        System.out.println("Got it. I've added this task: \n  " + t + "\nNow you have "
                + size + " tasks in the list.");
    }

    /**
     * Shows that a <code>Task</code> has been marked as done.
     * @param t The <code>Task</code> that is marked as done.
     */
    public void markedAsDone(Task t) {
        System.out.println("Nice! I've marked this task as done: \n  " + t);
    }

    /**
     * Shows that a <code>Task</code> has been removed, and displays the number 
     * of current tasks in the list.
     * @param t The <code>Task</code> that is deleted from the list.
     */
    public void removedTask(Task t, int size) {
        System.out.println("Noted. I've removed this task: \n  " + t + "\nNow you have "
                + size + " tasks in the list.");
    }

    /**
     * Displays all tasks currently stored in the list.
     * @param tasks The <code>TaskList</code> used to store tasks.
     */
    public void printTasks(TaskList tasks) {
        int idx = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasks.getList()) {
            System.out.println(idx + "." + t);
            idx++;
        }
    }

    /**
     * Shows content of an error.
     * @param s A string that represents the content of the error.
     */
    public void showError(String s) {
        System.out.println(s);
    }

    /**
     * Shows bye message to user.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}




