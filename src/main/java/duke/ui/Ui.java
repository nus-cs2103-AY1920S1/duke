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
    private Scanner sc = new Scanner(System.in);
    private StringBuilder output = new StringBuilder();

    /**
     * This method reads user input and returns it in a String.
     *
     * @return The string format of user input.
     */
    public String userInput() {
        return this.sc.nextLine();
    }


    public void resetOutput() {
        this.output.delete(0, output.length());
    }

    /**
     * Returns the output string.
     */
    public String printString() {
        return output.toString();
    }

    /**
     * Adds the message on to the output string object.
     */
    private void append(String message) {
        output.append(message);
        output.append("\n");
    }

    /**
     * Check for data left unread.
     */
    public boolean isDoneReading() {
        return sc.hasNextLine();
    }

    /**
     * This method prints welcome message when app is initiated.
     */
    public void showWelcome() {
        append("Hello, I'm Duke\nWhat can I do for you?");
    }

    /**
     * This method prints out the tasks currently in the task list.
     *
     * @param tasks The task list.
     */
    public void printTaskList(ArrayList<? extends Task> tasks) {
        assert (tasks != null && tasks.size() != 0) : "You have no tasks to show: add a todo/deadline/event now !";
        int counter = 0;
        append("Here are the tasks in your list:");
        for (Task task : tasks) {
            counter++;
            append(counter + "." + task.toString());
        }
    }

    /**
     * This method prints the exit message to the user.
     */
    public void printBye() {

        append("Bye. Hope to see you again soon!");
        append("Press enter to exit the chat!");
    }

    /**
     * This method prints all the messages for marking task as done.
     *
     * @param task The description of the task.
     */
    public void printDone(String task) {
        append("Nice! I've marked this task as done: ");
        append(task);
    }

    /**
     * This method prints all the tasks removal messages.
     *
     * @param task The description of the task.
     * @param size The size of the task list.
     */
    public void printRemoveMessage(String task, int size) {
        append("Noted. I've removed this task:");
        append(task);
        append("Now you have " + size + " tasks in the list.");
    }

    /**
     * This method prints all tasks adding messages.
     *
     * @param task The description of the task.
     * @param size The size of the task list.
     */
    public void printAddTaskMessage(String task, int size) {
        append("Got it. I've added this task.");
        append(task);
        append("Now you have " + size + " tasks in the list.");
    }

    /**
     * Append error message to the output string.
     */
    public void printError(String error) {
        append(error);
    }

    /**
     * Append clear tasklist message and prints it out to the users.
     */
    public void printDeleteAllMessage() {
        append("Noted. I've removed ALL your tasks!");
        append("Now you have 0 tasks in the list.");
    }
}