package duke.command;

import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * A class which deals with the interactions of the user, including printing and requesting for input.
 */
public class Ui {
    /**
     * Prints the welcome message.
     */
    public static String printWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Prints the error message due to file not found.
     */
    public String showLoadingError() {
        return "Error: Unable to load. File not found. Empty list is created.";
    }

    /**
     * Prints each task in the list provided.
     *
     * @param list The list to be printed.
     */
    public String printList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            return "No task found";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            IntStream.rangeClosed(1, list.size()).forEach(x -> {
                Task task = list.get(x - 1);
                sb.append(x).append(". ").append(task.toString()).append("\n");
            });
            return sb.toString();
        }
    }

    /**
     * Prints a message to remind user that a task has been marked as done.
     *
     * @param task The task to be marked as done.
     */
    String printTaskDone(Task task) {
        return ("Nice! I've marked this task as done:\n  " + task.toString());
    }

    /**
     * Prints a message to remind user that a task has been removed from the list.
     *
     * @param removed The deleted task.
     * @param list The list, in which the task has been removed from.
     */
    String printDeleteTask(Task removed, ArrayList<Task> list) {
        return ("Noted. I've removed this task:\n" +
                "  " + removed.toString() + "\n" +
                "Now you have " + list.size() + " in the list.");
    }

    /**
     * Prints a message to remind user that a task has been added to the list.
     *
     * @param task The task to be added.
     * @param list The list, to which the task is to be added to.
     */
    String printAddTask(Task task, ArrayList<Task> list) {
        return ("Got it. I've added this task:\n" +
                "  " + task.toString() + "\n" +
                "Now you have " + list.size() + " tasks in the list");
    }
}
