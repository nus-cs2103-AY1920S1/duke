package Ui;

import Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = "Hello from\n" + LOGO;
    private static final String DIVIDER_LINE = "_______";

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Scans for user input and returns the result as a String.
     * @return Command inputted as a String
     */
    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Prints a message once a task has been added.
     * @param numOfTasks total number of tasks after a task has been added
     * @param taskDescription description of the newly added task
     */
    public void showAddTaskMsg(int numOfTasks, String taskDescription) {
        System.out.println("Got it. I've added this task:\n  " + taskDescription);
        if (numOfTasks == 1) {
            System.out.println("Now you have " + numOfTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        }
    }

    /**
     * Prints a message once a task has been deleted
     * @param removedTask description of removed task
     * @param numOfTasks total number of tasks that are left in the list
     */
    public void showDelTaskMsg(String removedTask, int numOfTasks) {
        System.out.println("Noted. I've removed this task:\n  " + removedTask);
        if (numOfTasks == 1) {
            System.out.println("Now you have " + numOfTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        }
    }

    /**
     * Prints the tasks stored in a list.
     * @param tasks arraylist of tasks stored.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }


    /**
     * Prints a message once the command find has been entered.
     */
    public void showFindMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Displays a message once command 'Bye' has been entered.
     */
    public void showByeMessage() {
        System.out.println("Bye! Hope to see you again!");
    }

    /**
     * Displays a message once task is done.
     * @param task task that is done
     */
    public void showDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    [" + task.getStatusIcon() + "] " +
                task.getDescription());
    }

    /**
     * Prints an error message if there is a loading error.
     */
    public void showLoadingError() {
    }

    /**
     * Prints an error message.
     * @param errorMessage description of error message
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a line divider.
     */
    public void showLine() {
        System.out.println(DIVIDER_LINE);
    }
}
