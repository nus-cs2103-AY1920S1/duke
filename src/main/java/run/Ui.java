package run;

import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all the interaction with the user (taking input and printing output)
 */
public class Ui {
    protected Scanner sc;

    /**
     * Prints an error message to the user
     * @param errorMessage the error message to be printed to the user
     */
    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a message to the user
     * @param message the message to be printed
     */
    public static void showMessage(String message) {
        System.out.println("---------------------------------------------------------------------");
        System.out.println(message);
        System.out.println("---------------------------------------------------------------------");
    }

    /**
     * Introduction that is printed when Duke is first accessed
     */
    public void introduction() {
        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Initialises a scanner (Used when Duke is first accessed)
     */
    public void init() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints exit message (Used when Duke is exited)
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Closes the scanner (Used when Duke is exited)
     */
    public void closeScanner() {
        this.sc.close();
    }

    /**
     * Prints line space for readability
     */
    public void showLine() {
        System.out.println("---------------------------------------------------------------------");
    }

    /**
     * Reads the line that user inputs
     * @return rawString of user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a list of tasks
     * @param tasks arraylist of tasks to be printed
     */
    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= tasks.size(); i++) {
            Task curr_task = tasks.get(i-1);
            System.out.println(i + "." + curr_task);
        }
    }

    /**
     * Prints messages for when task is added to TaskList
     * @param task the task that was added
     * @param size current number of tasks in TaskList
     */
    public static void printAdd(Task task, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints message for when a task is marked as done
     * @param task the task to be marked as done
     */
    public static void printDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + task);
    }

    /**
     * Prints messages for when a task is deleted from TaskList
     * @param task the task to be deleted
     * @param size new total number of tasks in TaskList
     */
    public static void printDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    public static void printFind(ArrayList<Task> tasks) {
        if(tasks.size() == 0) {
            System.out.println("No tasks found containing your search!");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 1; i <= tasks.size(); i++) {
            Task curr_task = tasks.get(i-1);
            System.out.println(i + "." + curr_task);
        }
    }
}