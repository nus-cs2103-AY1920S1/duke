package duke.util;

import duke.task.Task;

import java.util.Scanner;

/**
 * Handles the print messages for user interaction.
 */
public class Ui {

    private Scanner sc = new Scanner(System.in);
    private String line = "    ____________________________________________________________";

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        System.out.println(line + "\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n" + line);
    }

    /**
     * Shows the error message for loading errors.
     * @param e error message when caught
     */
    public void showLoadingError(String e) {
        System.err.println(e);
    }

    /**
     * Shows a general error message.
     * @param e error message when caught
     */
    public void showError(String e) {
        System.err.println(e);
    }

    /**
     * Reads the next line of user input.
     * @return a string representation of user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the line breaks in between commands.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Shows the message when user deletes a task.
     * @param task deleted task object
     * @param numOfTasks number of tasks left on the list
     */
    void showDeleted(Task task, int numOfTasks) {
        System.out.println(
                "     Noted. I've removed this task: \n       " + task
                        + "\n     Now you have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Displays the farewell message when the user exits the program.
     */
    public void showBye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks.
     * @param taskList taskList object to be shown
     */
    public void showList(TaskList taskList) {
        System.out.print(taskList);
    }

    /**
     * Displays a message in response to adding of tasks.
     * @param task task to be added
     * @param numOfTasks current number of tasks
     */
    void showAdded(Task task, int numOfTasks) {
        System.out.println("     Got it. I've added this task: \n       " + task
                + "\n     Now you have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Displays a message when the user marks a task as done.
     * @param task task marked as done
     */
    void showDone(Task task) {
        System.out.println("     Nice! I've marked this task as done: \n       " + task);
    }
}