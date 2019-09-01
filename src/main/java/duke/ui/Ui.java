package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The user interaction reactor.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in); //The scanner scans user command.

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads user command.
     *
     * @return User's command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints loading error message.
     */
    public void showLoadingError() {
        System.out.println("There is some error in the file");
    }

    /**
     * Shows error message.
     *
     * @param errorMessage Error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints exit message.
     */
    public void showLeaving() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints tasks.
     *
     * @param tasks Tasks to be printed.
     */
    public void listTasks(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        for (int i = 0; i < list.size(); i++) {
            String todo = String.format("%d. %s", i + 1, list.get(i).toString());
            System.out.println(todo);
        }
    }

    /**
     * Prints done message.
     *
     * @param task The task that is done.
     */
    public void showDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        System.out.println(task.toString());
    }

    /**
     * Prints delete message.
     *
     * @param task The task be deleted.
     * @param listSize The size of the tasklist.
     */
    public void showDelete(Task task, int listSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", listSize));
    }

    /**
     * Prints add message.
     *
     * @param task The task to be added.
     * @param listSize The size of the tasklist.
     */
    public void showAdd(Task task, int listSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", listSize));
    }
}
