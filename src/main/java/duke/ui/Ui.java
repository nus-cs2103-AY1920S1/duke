package duke.ui;

import duke.task.Task;
import duke.exception.DukeException;

import java.util.List;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

    /**
     * Constructor of Ui object. Creates scanner object for the instance.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads the user input and returns it.
     * @return The user command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints error message.
     * @param error Error message to be reflected to the user.
     */
    public void showError(String error) {
        System.err.println(error);
    }

    /**
     * Prints the welcome message and the existing tasks if any.
     * @param tasks The existing task list in the database.
     */
    public void showWelcome(List<Task> tasks) {
        showLine();
        System.out.println("     Hello! I'm Duke\n" + "     What can I do for you?\n");

        try {
            if (!tasks.isEmpty()) {
                printTasks(tasks);
            } else {
                System.out.println("     There are no tasks in the list right now.");
            }
        } catch (DukeException e) {
            showError(e.getMessage());
        } finally {
            showLine();
        }
    }

    /**
     * Prints the exit message.
     */
    public void showExit() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * Prints the available tasks.
     * @param tasks Current task list to be printed.
     * @throws DukeException If the current task list is empty.
     */
    public void printTasks(List<Task> tasks) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("     ☹ OOPS!!! The list is empty.");
        }
        System.out.println("     Here are the tasks in your list:");
        int count = 0;
        for (Task task: tasks) {
            count++;
            System.out.println("     " + count + "." + task);
        }
    }


    /**
     * Prints the tasks that match the user's keyword.
     * @param tasks The list of tasks.
     * @param keyword User's keyword.
     * @throws DukeException If there are no tasks in the list or no search results.
     */
    public void printMatchingTasks(List<Task> tasks, String keyword) throws DukeException {
        try {
            keyword = keyword.substring(4).trim();
            if (keyword.isEmpty()) {
                throw new DukeException("     ☹ OOPS!!! Search keyword cannot be empty.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! Search keyword cannot be empty.");
        }
        if (tasks.isEmpty()) {
            throw new DukeException("     ☹ OOPS!!! The list is empty.");
        }
        System.out.println("     Here are the tasks in your list:");
        int count = 0;
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                count++;
                System.out.println("     " + count + "." + task);
            }
        }
        if (count == 0) {
            throw new DukeException("     ☹ OOPS!!! There are no matching tasks.");
        }
    }

    /**
     * Prints error if there is no database.
     */
    public void showLoadingError() {
        showError("     No existing task list available!");
    }

    /**
     * Prints the set of sentences after successfully adding a task.
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void addedTask(Task task, int size) {
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the set of sentences after successfully deleting a task.
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public void deletedTask(Task task, int size) {
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the set of sentences after successfully marking a task as done.
     * @param tasks The task that was marked as done.
     * @param number The index of the task in the list.
     */
    public void doneTask(List<Task> tasks, int number) {
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + tasks.get(number - 1));
    }

    /**
     * Prints the horizontal line.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }
}
