package main;

import task.Task;

import java.util.ArrayList;

/**
 * Represents the printing of outputs.
 */
public class Ui {

    /**
     * Prints out the contents of a list.
     *
     * @param arr The array to print.
     * @return String of the output.
     */
    public String printList(ArrayList<Task> arr) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < arr.size(); i++) {
            output = output + (i + 1) + "." + arr.get(i) + "\n";
        }
        return output;
    }

    /**
     * Prints out the command to add a task to an array.
     *
     * @param arr The array to be added to.
     * @return String of the output.
     */
    public String printAdd(ArrayList<Task> arr) {
        String output = "Got it. I've added this task: \n";
        output += arr.get(arr.size() - 1) + "\n";
        output += "Now you have " + arr.size() + " tasks in the list.\n";
        return output;
    }

    /**
     * Prints out the done command.
     *
     * @param arr   The array to print.
     * @param index The index of the task in the list to be marked as done.
     * @return String of the output.
     */
    public String printDone(ArrayList<Task> arr, int index) {
        arr.get(index).markAsDone();

        String output = "Nice! I've marked this task as done: \n" + arr.get(index);
        return output;
    }

    /**
     * Prints out the command to remove an element from the array.
     *
     * @param arr  The array to print.
     * @param task The task to be removed.
     * @return String of the output.
     */
    public String printRemove(ArrayList<Task> arr, Task task) {

        String output = "Noted. I've removed this task: \n" + task + "\n";
        output = output + "Now you have " + arr.size() + " tasks in the list.\n";
        return output;
    }

    /**
     * Returns the bye message.
     */
    public String printBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out the error message.
     *
     * @param errorMessage The error message to be printed out.
     * @return String of the output.
     */
    public String printError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints out the message.
     *
     * @param message The error message to be printed out.
     * @return String of the output.
     */
    public String printMessage(String message) {
        return message;
    }

    /**
     * Prints out the list of tasks with the search keyword.
     *
     * @param arr The array where the search takes place.
     * @return String of the output.
     */
    public String printFound(ArrayList<Task> arr) {
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < arr.size(); i++) {
            output = output + (i + 1) + "." + arr.get(i) + "\n";
        }
        return output;
    }
}
