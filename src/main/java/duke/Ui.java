package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    private static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_INDEX = "Here are the tasks in your list:";
    private static final String MESSAGE_FIND = "Here are the matching tasks in your list:";
    private static final String MESSAGE_ADD = "Got it. I've added this task:";
    private static final String MESSAGE_DELETE = "Noted. I've removed this task:";
    private static final String MESSAGE_DONE = "Nice! I've marked this task as done:";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        String command = scanner.nextLine().trim();
        return command;
    }

    public void showWelcome() {
        System.out.println(MESSAGE_WELCOME);
        showLine();
    }

    public void showExit() {
        System.out.println(MESSAGE_EXIT);
        scanner.close();
    }

    /**
     * Prints the list containing all tasks.
     *
     * @param tasks All the tasks in the list
     */
    public void showIndexMsg(TaskList tasks) {
        if (tasks.isEmpty()) {
            showNoTasksMsg();
        } else {
            System.out.println(MESSAGE_INDEX);
            showTaskList(tasks);
        }
    }

    /**
     * Prints the list containing all tasks matching keyword.
     *
     * @param tasks Filtered tasks.
     */
    public void showFindMsg(TaskList tasks) {
        if (tasks.isEmpty()) {
            showNoTasksMsg();
        } else {
            System.out.println(MESSAGE_FIND);
        }
        showTaskList(tasks);
    }

    /**
     * Prints a list of tasks.
     *
     * @param tasks A list of tasks to be printed
     */
    public void showTaskList(TaskList tasks) {
        ArrayList<Task> allTasks = tasks.getTasks();
        for (int i = 1; i <= allTasks.size(); i++) {
            Task task = allTasks.get(i - 1);
            System.out.printf("%d.%s\n", i, task);
        }
    }

    public void showTaskCompletionMsg(Task task) {
        System.out.println(MESSAGE_DONE);
        showSingleTask(task);
    }

    /**
     * Prints a message upon successful addition of a task to the list.
     *
     * @param task  The task added to the list
     * @param tasks The list of tasks after the addition
     */
    public void showTaskAdditionMsg(Task task, TaskList tasks) {
        System.out.println(MESSAGE_ADD);
        showSingleTask(task);
        showTaskTotal(tasks);
    }

    /**
     * Prints a message upon successful deletion of a task from the list.
     *
     * @param task  The task removed from the list
     * @param tasks The list of tasks after the deletion
     */
    public void showTaskDeletionMsg(Task task, TaskList tasks) {
        System.out.println(MESSAGE_DELETE);
        showSingleTask(task);
        showTaskTotal(tasks);
    }

    public void showLoadingError() {
        System.out.println("Failed to load tasks. An empty list is created.");
    }

    public void showError(String message) {
        System.out.printf("\u2639 OOPS!!! %s\n", message); //show frowning face
    }

    public void showLine() {
        // A platform independent line separator.
        System.out.print(System.lineSeparator());
    }

    private void showNoTasksMsg() {
        System.out.println("There are currently no tasks in your list.");
    }

    private void showSingleTask(Task task) {
        System.out.printf("  %s\n", task);
    }

    private void showTaskTotal(TaskList tasks) {
        int total = tasks.size();
        System.out.printf("Now you have %d task%s in the list.\n", total, total > 1 ? "s" : "");
    }
}
