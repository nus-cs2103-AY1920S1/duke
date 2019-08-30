/**
 * The Ui class handles the chat bot's responses to the user, namely what it should print back to the user.
 */

import java.util.ArrayList;

public class Ui {
    /**
     * The Duke logo.
     */
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    /**
     * A line that separates user input from chat bot output.
     */
    String line = "________________________________________";
    /**
     * An indentation of 4 spaces. All chat bot output are indented by 4 spaces.
     */
    String indent = "    ";

    public Ui() {
    }

    /**
     * Prints the welcome message and logo.
     */
    public void showLogo() {
        System.out.println("Hello from\n" + logo);
        System.out.println(indent + line);
        System.out.println(indent + "Hello! I'm Duke.\n" + indent + "What can I do for you today?");
        System.out.println(indent + line);
    }

    public void showLoadingError() {
        System.out.println("Hello from\n" + logo);
        System.out.println(indent + line);
        System.out.println(indent + "Duke has failed to load properly.");
        System.out.println(indent + line);
    }
    /**
     * Prints the goodbye message.
     */
    public void showByeResponse() {
        System.out.println(indent + "Bye! Hope to see you again soon.");
    }

    /**
     * If tasks are present, this is the message to introduce the tasks.
     */
    public void showListResponse() {
        System.out.println(indent + "Here are the tasks in your list:");
    }

    /**
     * If no tasks are present, this is the message to state that there are no tasks.
     */
    public void showNoTaskResponse() {
        System.out.println(indent + "You have no tasks.");
    }

    /**
     * Prints an indented line.
     */
    public void printLine() {
        System.out.println(indent + line);
    }

    /**
     * Prints the message that responses to a task being marked as done.
     */
    public void showDoneResponse() {
        System.out.println(indent + "Nice! I've marked this task as done:");
    }

    /**
     * Prints the message that responses to the user removing a task.
     * @param deletedTask An integer representing the index of the task that the user wants to remove.
     */
    public void showDeleteResponse(Task deletedTask) {
        System.out.println(indent + "Noted. I've removed the following task:");
        System.out.println(indent + indent + deletedTask);
    }

    /**
     * Prints the total number of tasks that are in the list.
     * @param list A list of tasks present.
     */
    public void showTotalNumberTasks(ArrayList<Task> list) {
        System.out.println(indent + "Now you have " + list.size() + " task(s) in the list.");
    }

    /**
     * Prints the message that responses to the user adding a task to the list.
     * @param task
     * @param list
     */
    public void printAddedTask(Task task, ArrayList<Task> list) {
        System.out.println(indent + "Got it. I've added this task:");
        System.out.println(indent + indent + task);
        System.out.println(indent + "Now you have " + list.size() + " task(s) in the list.");
    }

    public void printQuerySet(ArrayList<Task> list, String search) {
        System.out.println(indent + "Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            int k = i + 1;
            if (task.description.contains(search)) {
                System.out.println(indent + k + ". " + task);
            }
        }
    }

    /**
     * Prints all tasks and index them.
     * @param tasks A TaskList object that contains the list of class.
     */
    public void printTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getList().size(); i++) {
            int k = i + 1;
            System.out.println(indent + k + ". " + tasks.list.get(i).toString());
        }
    }
}