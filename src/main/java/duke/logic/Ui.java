package duke.logic;

import duke.model.task.Task;

import java.util.ArrayList;
import java.util.stream.IntStream;


/**
 * A class which deals with the interactions of the user, including printing and requesting for input.
 */
public class Ui {
    /**
     * Returns the welcome message.
     *
     * @return Returns the welcome message.
     */
    public static String printWelcome() {
        return "Hello! I'm PikaTodo.\nWhat can I do for you?";
    }

    /**
     * Returns the error message due to file not found.
     *
     * @return Returns the error message due to file not found.
     */
    public String showLoadingError() {
        return "Error: Unable to load. File not found. Empty list is created.";
    }

    /**
     * Returns a string response containing the string representation of the list of tasks.
     *
     * @param list The list to be printed.
     * @return Returns a string response containing the string representation of the list of tasks.
     */
    public String printList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            return "No task found.";
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
     * Returns a message to remind user that a task has been marked as done.
     *
     * @param task The task to be marked as done.
     * @return Returns a message to remind user that a task has been marked as done.
     */
    String printMarkDone(Task task) {
        return ("Nice! I've marked this task as done:\n  " + task.toString());
    }

    /**
     * Returns a message to remind user that a task has been removed from the list.
     *
     * @param removed The deleted task.
     * @param list The list, in which the task has been removed from.
     * @return Returns a message to remind user that a task has been removed from the list.
     */
    String printDeleteTask(Task removed, ArrayList<Task> list) {
        return ("Noted. I've removed this task:\n"
                + "  " + removed.toString() + "\n"
                + "Now you have " + list.size() + " task" + (list.size() > 1 ? "s" : "") + " in the list.");
    }

    /**
     * Returns a message to remind user that a task has been added to the list.
     *
     * @param task The task to be added.
     * @param list The list, to which the task is to be added to.
     * @return Returns a message to remind user that a task has been added to the list.
     */
    String printAddTask(Task task, ArrayList<Task> list) {
        return ("Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + list.size() + " task" + (list.size() > 1 ? "s" : "") + " in the list");
    }

    /**
     * Returns a message to remind user that a task has been updated.
     *
     * @param task The task to be updated.
     * @return Returns a message to remind user that a task has been updated.
     */
    String printUpdateTask(Task task) {
        return ("Noted. I've edited this task:\n"
                + "  " + task.toString() + "\n");
    }

    /**
     * Returns a message to help user with the available commands.
     *
     * @return Returns a message to help users with the available commands.
     */
    String printHelp() {
        return ("Available commands:\n"
                + "\"list\"\n"
                + "\"done <index>\"\n"
                + "\"delete <index>\"\n"
                + "\"find <string>\"\n"
                + "\"update <index> w/<description> d/<date>\"\n"
                + "\"clear\"\n"
                + "\"todo <name>\"\n"
                + "\"deadline <name> /by <date> <time>\"\n"
                + "\"event <name> /at <date> <time>\"\n"
                + "\"bye\"\n");
    }

    /**
     * Returns a string to remind user that all tasks has been cleared.
     *
     * @return Returns a string to remind user that all tasks has been cleared.
     */
    String printClearTask() {
        return ("All task cleared!");
    }
}
