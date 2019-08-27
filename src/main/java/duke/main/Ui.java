package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    Ui() {
        sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a success message after a task is successfully added to the list.
     *
     * @param task Task added to list.
     * @param size Current size of list after addition.
     */
    public void printAddSuccessMessage(Task task, int size) {
        showLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Prints a success message after a task is deleted from to the list.
     *
     * @param task Task deleted from list.
     * @param size Current size of list after deletion.
     */
    public void printDeleteSuccessMessage(Task task, int size) {
        showLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Prints the given list with formatting.
     *
     * @param tasks List of tasks to be printed.
     */
    public void printList(ArrayList<Task> tasks) {
        showLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int oneBasedIndex = i + 1;
            System.out.printf("\t%d. %s\n", oneBasedIndex, tasks.get(i));
        }
        showLine();
    }

    void printGreetingMessage() {
        printMessage("Hello, I'm Duke\n\tWhat can I do for you?");
    }

    public void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    void showError(String message) {
        printMessage(message);
    }

    void showLoadingError(String error) {
        printMessage("An error occurred during file loading " + error);
    }

    /**
     * Prints a given message with formatting.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        showLine();
        System.out.println("\t" + message);
        showLine();
    }

    private void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    String readCommand() {
        return sc.nextLine();
    }
}
