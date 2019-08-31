package duke.ui;

import java.util.ArrayList;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The Class which handles user input and interacts with the user.
 */
public class Ui {

    private String output;

    /**
     * Prints the output message to be sent and resets the output message.
     */
    public String print() {
        return output;
    }

    /**
     * Resets the output message
     */
    public void reset() {
        output = "";
    }

    /**
     * Appends the string to the output meesage.
     */
    public void append(String message) {
        output += (message + "\n");
    }

    /**
     * Shows the task which the user has completed.
     * @param task The completed task.
     */
    public void showCompletedTask(Task task) {
        append("Nice! I've marked this task as done:");
        append(task.toString());
    }

    /**
     * Shows the task which the user has deleted.
     * @param task The deleted task.
     */
    public void showDeletedTask(Task task) {
        append("Noted. I've removed this task: ");
        append(task.toString());
        append("Now you have " +  TaskList.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Shows the task which the user has added.
     * @param task The added task.
     */
    public void showAddedTask(Task task) {
        append("Got it. I've added this task: ");
        append(task.toString());
        append("Now you have " + TaskList.getNumberOfTasks() + " tasks in the list!");
    }

    /**
     * Sends a simple greeting to the user.
     */
    public void sendGreeting() {
        append("Hello! Martin here!\nWhat can I do");
    }

    /**
     * Sends a simple farewell message to the user.
     */
    public void sendFarewell() {
        append("Bye. Hope to see you again soon!");
        append("Press enter to keep the app closed.");
    }

    /**
     * Sends a message to the user.
     * @param taskIndexes Represents the list of matching tasks indexes.
     */
    public void showMatchingTasks(ArrayList<Integer> taskIndexes) {
        append("Here are the matching tasks in your list:");
        for (int i = 0; i < taskIndexes.size(); i++) {
            Task task = TaskList.getTask(taskIndexes.get(i));
            append(task.toString());
        }
    }

    /**
     * Sends a message to the user based on the DukeException given.
     * @param error The error which has arised.
     */
    public void sendErrorMessage(DukeException error) {
        append(error.toString());
    }

    /**
     * Sends a Loading error in the event where there is no tasks to be read from the file.
     */
    public void showLoadingError() {
        append("OOPS !!! Loading Error! Creating a New List...");
    }

    /**
     * Lists all the tasks involved in the TaskList.
     */
    public void listTasks() {
        if (TaskList.getNumberOfTasks() ==  0) {
            append("You have no tasks in the list!");
        } else {
            append("Here are the tasks in your list:");
        }
        for (int tasknum = 0; tasknum < TaskList.getNumberOfTasks(); tasknum++) {
            Task task = TaskList.getTask(tasknum);
            String todo = task.toString();
            if (task.isCompleted) {
                append((tasknum + 1) + ". " + todo);
            } else {
                append((tasknum + 1) + ". " + todo);
            }
        }
    }
}
