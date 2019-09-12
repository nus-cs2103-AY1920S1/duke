package duke.task;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    public Ui() {

    }

    public void printHello() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printDone(Task task) {
        String output = "Nice! I've marked this task as done:\n  " + task;
        System.out.println(output);
    }

    /**
     * This function prints out the confirmation message after a successful deletion.
     * @param task The Task deleted.
     * @param counter The number of tasks after deletion.
     */
    public void printDelete(Task task, int counter) {
        String output = "Noted. I've removed this task:\n  " + task;
        output += "\nNow you have " + counter + " tasks in the list.";
        System.out.println(output);
    }

    /**
     * This function prints out the confirmation message after a successful addition.
     * @param task The Task added.
     * @param counter The number of tasks after addition.
     */
    public void printAdd(Task task, int counter) {
        String output = "Got it. I've added this task:\n  " + task;
        output += "\nNow you have " + counter + " tasks in the list.";
        System.out.println(output);
    }

    /**
     * This function prints out the list of tasks that correspond to the search term.
     * @param results The result of the search.
     */
    public void printFind(String results) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        sb.append(results);
        System.out.print(sb.toString());
    }

    /**
     * This function prints out the given error message.
     * @param message The exception message.
     */
    public static void printError(String message) {
        System.err.println(message);
    }

    /**
     * This function prints out an error message when an error occurred during the loading of the storage file.
     */
    public void showLoadingError() {
        printError("An error occurred with loading the input file, using an empty one instead.");
    }
}
