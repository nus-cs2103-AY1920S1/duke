package core;

import task.Task;
import task.TaskList;

import java.util.Scanner;

public class Ui {

    public Ui() {
    }

    /**
     * Read a string input from user.
     *
     * @return String containing user input.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints out message to user.
     *
     * @param message String containing message.
     */
    public void show(String message) {
        showLine();
        System.out.println("\t" + message);
        showLine();
    }

    private void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints out message for adding a task.
     */
    public void showAddTask(Task task, int taskCount) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Got it mate. I've added this task:\n\t" + task + "\n\t");
        messageBuilder.append("Now you have " + taskCount + " tasks in the list mate.");
        show(messageBuilder.toString());
    }

    /**
     * Prints out message for setting a task to done.
     *
     * @param task Task to set as done.
     */
    public void showDoneTask(Task task) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Nice one mate! I've marked this task as done:\n\t");
        messageBuilder.append("  " + task);
        show(messageBuilder.toString());
    }

    /**
     * Prints out message for deleting a task.
     *
     * @param task      Task to delete.
     * @param taskCount Current number of tasks in list.
     */
    public void showDeleteTask(Task task, int taskCount) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Noted mate! I've removed this task:\n\t")
                .append("  " + task + "\n\t")
                .append("Now you have " + taskCount + " tasks in the list mate.");
        show(messageBuilder.toString());
    }

    /**
     * Prints out list of tasks.
     *
     * @param tasks TaskList to print out.
     */
    public void listTasks(TaskList tasks) {
        StringBuilder messageBuilder = new StringBuilder();

        if (tasks.size() > 0) {
            messageBuilder.append("Here are the tasks in your list:\n\t");
            for (int i = 0; i < tasks.size(); i++) {
                messageBuilder.append((i + 1) + ". " + tasks.get(i) + "\n\t");
            }
            messageBuilder.setLength(messageBuilder.length() - 2); // strip trailing \n\t
        } else {
            messageBuilder.append("Can't see any tasks in the list, start adding tasks mate!");
        }

        show(messageBuilder.toString());
    }

    /**
     * Prints out list of tasks matching search string.
     *
     * @param matchingTasks TaskList to print out.
     */
    public void showFoundTasks(TaskList matchingTasks) {
        StringBuilder messageBuilder = new StringBuilder();

        if (matchingTasks.size() > 0) {
            messageBuilder.append("Here are the matching tasks in your list:\n\t");
            for (int i = 0; i < matchingTasks.size(); i++) {
                messageBuilder.append((i + 1) + ". " + matchingTasks.get(i) + "\n\t");
            }
            messageBuilder.setLength(messageBuilder.length() - 2); // strip trailing \n\t
        } else {
            messageBuilder.append("Mate, I found no matching tasks. Try something else.");
        }

        show(messageBuilder.toString());
    }

    /**
     * Prints out loading error.
     */
    public void showLoadingError() {
        showError("Oops! Unable to load tasks from hard disk, starting with an empty list.");
    }

    /**
     * Prints out welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n\t"
                + "|  _ \\ _   _| | _____ \n\t"
                + "| | | | | | | |/ / _ \\\n\t"
                + "| |_| | |_| |   <  __/\n\t"
                + "|____/ \\__,_|_|\\_\\___|\n\t";
        String greeting = logo + "G'day mate! I'm Duke.\n\tWhatcha need help with?";
        show(greeting);
    }

    /**
     * Prints out exit message.
     */
    public void showBye() {
        String bye = "Bye mate. Catch you later!";
        show(bye); //say goodbye
    }

    /**
     * Prints out generic error.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        show(message);
    }

}
