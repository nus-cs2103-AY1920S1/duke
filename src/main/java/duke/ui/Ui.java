package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The Class which handles user input and interacts with the user.
 */
public class Ui {

    /**
     * Sends a simple line for formatting.
     */
    public void sendLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows the task which the user has completed.
     * @param task The completed task.
     */
    public void showCompletedTask(Task task) {
        sendLine();
        sendMessage("Nice! I've marked this task as done:");
        sendMessage("  " + task.toString());
        sendLine();
    }

    /**
     * Shows the task which the user has deleted.
     * @param task The deleted task.
     */
    public void showDeletedTask(Task task) {
        sendLine();
        sendMessage("Noted. I've removed this task: ");
        sendMessage("  " + task.toString());
        sendMessage("Now you have " +  TaskList.getNumberOfTasks() + " tasks in the list.");
        sendLine();
    }

    /**
     * Shows the task which the user has added.
     * @param task The added task.
     */
    public void showAddedTask(Task task) {
        sendLine();
        sendMessage("Got it. I've added this task: ");
        sendMessage("  " + task.toString());
        sendMessage("Now you have " + TaskList.getNumberOfTasks() + " tasks in the list");
        sendLine();
    }

    /**
     * Sends a simple greeting to the user.
     */
    public void sendGreeting() {
        sendLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" I'm created by @seanlowjk");
        System.out.println(" What can I do for you?");
        sendLine();
    }

    /**
     * Sends a simple farewell message to the user.
     */
    public void sendFarewell() {
        sendLine();
        System.out.println(" Bye. Hope to see you again soon!");
        sendLine();
    }

    /**
     * Sends a message to the user.
     * @param input The text of the message.
     */
    public void sendMessage(String input) {
        System.out.println(" " + input);
    }

    /**
     * Sends a message to the user based on the DukeException given.
     * @param error The error which has arised.
     */
    public void sendErrorMessage(DukeException error) {
        sendLine();
        sendMessage(error.toString());
        sendLine();
    }

    /**
     * Sends a Loading error in the event where there is no tasks to be read from the file.
     */
    public void showLoadingError() {
        sendLine();
        System.out.println(" OOPS !!! Loading Error! Creating a New List...");
        sendLine();
    }

    /**
     * Lists all the tasks involved in the TaskList.
     */
    public void listTasks() {
        sendLine();
        sendMessage("Here are the tasks in your list:");
        for (int tasknum = 0; tasknum < TaskList.getNumberOfTasks(); tasknum ++) {
            Task task = TaskList.getTask(tasknum);
            String todo = task.toString();
            if (task.isCompleted) {
                sendMessage((tasknum + 1) + "." + todo);
            } else {
                sendMessage((tasknum + 1) + "." + todo);
            }
        }
        sendLine();
    }
}
