package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private Scanner sc;

    /**
     * Instantiates a new Scanner object to read user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the app's welcome message.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Duke.\nWhat can I do for you?\n"
                + "To input dates and times for deadlines and events, "
                + "please use the format: 29/03/2019, 6:05pm";
        System.out.println(welcomeMessage);
    }

    /**
     * Prints the app's exit message.
     */
    public void showExitMessage() {
        System.out.println("Bye! Hope to see you again soon :)");
    }

    /**
     * Prints the given error message.
     * @param errorMessage The error message to print.
     */
    public void showError(String errorMessage) {
        System.err.println(errorMessage);
    }

    /**
     * Reads the next user input using Scanner.
     * @return The user input as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints each Task in the given TaskList.
     * @param tasks The TaskList containing the tasks to print.
     */
    public void printTaskList(TaskList tasks) {

        System.out.println("Here are the task(s) in your list:");

        String task;
        for (int i = 1; i <= tasks.size(); i++) {
            task = String.format("%d.%s", i, tasks.get(i));
            System.out.println(task);
        }

        if (tasks.size() == 0) {
            System.out.println("There are no tasks in your list right now.");
        }
    }

    /**
     * Prints the confirmation message for marking a Task as done.
     * @param task The Task marked as done.
     */
    public void printTaskMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("  %s", task));
    }

    /**
     * Prints the confirmation message for deleting a Task from the TaskList.
     * @param tasks The TaskList, containing the remaining tasks.
     * @param task The deleted Task.
     */
    public void printTaskDeleted(TaskList tasks, Task task) {
        System.out.println(String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                task, tasks.size()
        ));
    }

    /**
     * Prints the confirmation message for adding a new Task to the TaskList.
     * @param tasks The TaskList, containing all tasks including the newly added Task.
     * @param task The newly added Task.
     */
    public void printTaskAdded(TaskList tasks, Task task) {
        System.out.println(String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                task, tasks.size()
        ));
    }
}
