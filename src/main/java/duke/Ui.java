package duke;

import duke.TaskList;
import duke.task.Task;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Handles user's interactions with the bot's ui, printing appropriate messages when needed to indicate
 * the result of a command typed in by the user.
 */
public class Ui {

    /**
     * Prints loading error message if any exception thrown when file with tasks is loaded from storage or created.
     */
    public void showLoadingError() {
        System.out.println("\u2639 OOPS!!! Unable to load file. Try again!");
    }

    /**
     * Prints welcome message to user to prompt user input.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Reads user input and returns it.
     *
     * @return userInput that was scanned.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput;
    }

    /**
     * Prints line before and after the bot's response to a command.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints error message for any error thrown from the parsing or execution of a user command.
     *
     * @param error Error message to be printed to screen.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints exit message before the bot is closed.
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all tasks in the list when user inputs list command.
     *
     * @param taskList List of tasks.
     */
    public void showTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        LinkedList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Prints a modified task if the task's status has changed (to completed) when the user inputs done command.
     *
     * @param t Changed Task.
     */
    public void showChangedTask(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    /**
     * Prints a task that was deleted by the user through the delete command.
     *
     * @param t Deleted task.
     */
    public void showDeletedTask(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
    }

    /**
     * Prints number of tasks left in the list once a task has been deleted through the delete command.
     *
     * @param taskList List of tasks.
     */
    public void showNumTasks(TaskList taskList) {
        int size = taskList.getTasks().size();
        System.out.print("Now you have " + size);
        if (size == 1) {
            System.out.println(" task in the list.");
        } else {
            System.out.println(" tasks in the list.");
        }
    }

    /**
     * Prints a task that was added to task list through the commands deadline, event, or todo.
     *
     * @param task Added task.
     * @param tasks List of tasks.
     */
    public void printAddedTask(Task task, TaskList tasks) {
        int taskListSize = tasks.getTasks().size();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.print("Now you have " + taskListSize);
        if (taskListSize == 1) {
            System.out.println(" task in the list.");
        } else {
            System.out.println(" tasks in the list.");
        }
    }

}
