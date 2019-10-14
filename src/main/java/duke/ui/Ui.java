package duke.ui;

import duke.task.Task;

import duke.exception.DukeException;

import java.util.List;
import java.util.Scanner;

public class Ui {

    private Scanner sc;
    private StringBuilder sb;

    /**
     * Constructor of Ui object. Creates scanner object for the instance.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns error message.
     *
     * @param error Error message to be reflected to the user.
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Returns the welcome message.
     */
    public String showWelcome() {
        sb = new StringBuilder();
        sb.append("Hello! I'm Duke\n" + "What can I do for you? :)\n");
        return sb.toString();
    }

    /**
     * Returns the exit message.
     */
    public String showExit() {
        sb = new StringBuilder();
        sb.append("Bye. Hope to see you again soon!");
        return sb.toString();
    }

    /**
     * Returns the available tasks.
     *
     * @param tasks Current task list to be printed.
     * @throws DukeException If the current task list is empty.
     */
    public String printTasks(List<Task> tasks) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("OOPS!!! The list is empty.");
        }
        sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        int taskCounter = 0;
        for (Task task: tasks) {
            taskCounter++;
            sb.append("  " + taskCounter + ". " + task + "\n");
        }
        return sb.toString();
    }


    /**
     * Returns the tasks that match the user's keyword.
     *
     * @param tasks The list of tasks.
     * @param keyword User's keyword.
     * @throws DukeException If there are no tasks in the list or no search results.
     */
    public String printMatchingTasks(List<Task> tasks, String keyword) throws DukeException {
        try {
            keyword = keyword.substring(4).trim();
            if (keyword.isEmpty()) {
                throw new DukeException("OOPS!!! Search keyword cannot be empty.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Search keyword cannot be empty.");
        }
        if (tasks.isEmpty()) {
            throw new DukeException("OOPS!!! The list is empty.");
        }

        sb = new StringBuilder();
        sb.append("Here are the matching tasks:\n");
        int count = 0;
        int matchingCount = 0;
        for (Task task: tasks) {
            count++;
            if (task.getDescription().contains(keyword)) {
                matchingCount++;
                sb.append("  " + count + "." + task + "\n");
            }
        }
        if (matchingCount == 0) {
            throw new DukeException("OOPS!!! There are no matching tasks.");
        }
        return sb.toString();
    }

    /**
     * Return error if there is no database.
     */
    public void showLoadingError() {
        sb = new StringBuilder();
        sb.append("No existing task list available!\n");
    }

    /**
     * Return the set of sentences after successfully adding a task.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public String addedTask(Task task, int size) {
        sb = new StringBuilder();
        sb.append("Got it. I've added this task: \n");
        sb.append("  " + task + "\n");
        sb.append("Now you have " + size + " tasks in the list.\n");
        return sb.toString();
    }

    /**
     * Return the set of sentences after successfully deleting a task.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public String deletedTask(Task task, int size) {
        sb = new StringBuilder();
        sb.append("Noted. I've removed this task: \n");
        sb.append("  " + task + "\n");
        sb.append("Now you have " + size + " tasks in the list.\n");
        return sb.toString();
    }

    /**
     * Returns the set of sentences after successfully marking a task as done.
     *
     * @param tasks The task that was marked as done.
     * @param number The index of the task in the list.
     */
    public String doneTask(List<Task> tasks, int number) {
        sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done: \n");
        sb.append("  " + tasks.get(number - 1) + "\n");
        return sb.toString();
    }

    /**
     * Prints the horizontal line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}
