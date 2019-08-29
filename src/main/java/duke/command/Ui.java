package duke.command;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * A class which deals with the interactions of the user, including printing and requesting for input.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Reads the next line using scanner.
     *
     * @return Returns the line.
     */
    public String readLine() {
        return sc.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the error message due to file not found.
     */
    public void showLoadingError() {
        System.err.println("Error: Unable to load. File not found. Empty list is created.");
    }

    /**
     * Prints each task in the list provided.
     *
     * @param list The list to be printed.
     */
    public void printList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        IntStream.rangeClosed(1, list.size()).forEach(x -> {
            Task task = list.get(x - 1);
            System.out.println(x + "." + task.toString());
        });
    }

    /**
     * Prints a message to remind user that a task has been marked as done.
     *
     * @param task The task to be marked as done.
     */
    public void printTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    /**
     * Prints a message to remind user that a task has been removed from the list.
     *
     * @param removed The deleted task.
     * @param list The list, in which the task has been removed from.
     */
    public void printDeleteTask(Task removed, ArrayList<Task> list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removed.toString());
        System.out.println("Now you have " + list.size() + " in the list.");
    }

    /**
     * Prints a message to remind user that a task has been added to the list.
     *
     * @param task The task to be added.
     * @param list The list, to which the task is to be added to.
     */
    public void printAddTask(Task task, ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }
}
