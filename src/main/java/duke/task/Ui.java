package duke.task;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    public Ui() {

    }

    public static String printHello() {
        return printMessage("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static String printBye() {
        return printMessage("Bye. Hope to see you again soon!");
    }

    public String printDone(Task task) {
        String output = "Nice! I've marked this task as done:\n  " + task;
        return printMessage(output);
    }

    /**
     * This function prints out the confirmation message after a successful deletion.
     * @param task The Task deleted.
     * @param counter The number of tasks after deletion.
     * @return String
     */
    public String printDelete(Task task, int counter) {
        String output = "Noted. I've removed this task:\n  " + task;
        output += "\nNow you have " + counter + " tasks in the list.";
        return printMessage(output);
    }

    /**
     * This function prints out the confirmation message after a successful addition.
     * @param task The Task added.
     * @param counter The number of tasks after addition.
     * @return String
     */
    public String printAdd(Task task, int counter) {
        String output = "Got it. I've added this task:\n  " + task;
        output += "\nNow you have " + counter + " tasks in the list.";
        return printMessage(output);
    }

    /**
     * This function prints out the list of tasks that correspond to the search term.
     * @param results The result of the search.
     * @return String
     */
    public String printFind(String results) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        sb.append(results);
        if (sb.charAt(sb.length() - 1) == '\n') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return printMessage(sb.toString());
    }

    /**
     * This function prints out the given message.
     * @param message The message to print.
     * @return String
     */
    public static String printMessage(String message) {
        System.out.println(message);
        return message;
    }

    /**
     * This function prints out the given error message.
     * @param message The exception message.
     * @return String
     */
    public static String printError(String message) {
        System.err.println(message);
        return message;
    }

    /**
     * This function prints out an error message when an error occurred during the loading of the storage file.
     */
    public static String showLoadingError() {
        return printError("An error occurred with loading the input file, using an empty one instead.");
    }
}
