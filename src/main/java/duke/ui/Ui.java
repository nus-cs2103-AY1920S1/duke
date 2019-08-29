package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that handles interactions with the user.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class Ui {

    /**
     * Represents the scanner which reads user input.
     */
    private Scanner sc;

    /**
     * Class constructor.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * This method reads user input and returns it in a String.
     *
     * @return The string format of user input.
     */
    public String userInput() {
        return this.sc.nextLine();
    }

    /**
     * This method displays messages to user.
     *
     * @param message Message toi be displayed to user.
     */
    public void printFormat(String message) {
        System.out.println(message);
    }

    /**
     * This method prints welcome message when app is initiated.
     */
    public void showWelcome() {
        printFormat("Hello, I'm Duke\nWhat can I do for you?");
    }

    /**
     * This method prints out the tasks currently in the task list.
     *
     * @param tasks The task list.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        int counter = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            counter++;
            System.out.println(counter + "." + task.toString());
        }
    }

    /**
     * This method prints the exit message to the user.
     */
    public void printBye() {
        printFormat("Bye. Hope to see you again soon!");
    }

    /**
     * This method prints all the messages for marking task as done.
     *
     * @param task The description of the task.
     */
    public void printDone(String task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
    }

    /**
     * This method prints all the tasks removal messages.
     *
     * @param task The description of the task.
     * @param size The size of the task list.
     */
    public void printRemoveMessage(String task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * This method prints all tasks adding messages.
     *
     * @param task The description of the task.
     * @param size The size of the task list.
     */
    public void printAddTaskMessage(String task, int size) {
        System.out.println("Got it. I've added this task.");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints separator line in all output of <code>Duke</code>.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}