package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_INDEX = "Here are the tasks in your list:";
    private static final String MESSAGE_FIND = "Here are the matching tasks in your list:";
    private static final String MESSAGE_ADD = "Got it. I've added this task:";
    private static final String MESSAGE_DELETE = "Noted. I've removed this task:";
    private static final String MESSAGE_DONE = "Nice! I've marked this task as done:";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        String command = in.nextLine().trim();
        return command;
    }

    public void showWelcome() {
        out.println(MESSAGE_WELCOME);
        showLine();
    }

    public void showExit() {
        out.println(MESSAGE_EXIT);
        in.close();
    }

    /**
     * Prints the list containing all tasks.
     *
     * @param tasks all the tasks in the list
     */
    public void showIndexMsg(TaskList tasks) {
        if (tasks.isEmpty()) {
            showNoTasksMsg();
        } else {
            out.println(MESSAGE_INDEX);
            showTaskList(tasks);
        }
    }

    /**
     * Prints the list containing all tasks matching keyword.
     *
     * @param tasks filtered tasks.
     */
    public void showFindMsg(TaskList tasks) {
        if (tasks.isEmpty()) {
            showNoTasksMsg();
        } else {
            out.println(MESSAGE_FIND);
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
            out.printf("%d.%s\n", i, task);
        }
    }

    /**
     * Prints an acknowledge message upon successful marking complestion of a task
     * in the list.
     *
     * @param task the task marked as done
     */
    public void showTaskCompletionMsg(Task task) {
        out.println(MESSAGE_DONE);
        showSingleTask(task);
    }

    /**
     * Prints an acknowledge message upon successful addition of a task to the list.
     *
     * @param task  the task added to the list
     * @param tasks the list of tasks after the addition
     */
    public void showTaskAdditionMsg(Task task, TaskList tasks) {
        out.println(MESSAGE_ADD);
        showSingleTask(task);
        showTaskTotal(tasks);
    }

    /**
     * Prints an acknowledgement message upon successful deletion of a task from the list.
     *
     * @param task  the task removed from the list
     * @param tasks the list of tasks after the deletion
     */
    public void showTaskDeletionMsg(Task task, TaskList tasks) {
        out.println(MESSAGE_DELETE);
        showSingleTask(task);
        showTaskTotal(tasks);
    }

    public void showLoadingError() {
        out.println("An empty data file duke.txt is created in the current directory.");
    }

    public void showError(String message) {
        out.printf("\u2639 OOPS!!! %s\n", message); //show frowning face
    }

    /**
     * Prints a platform independent line separator.
     */
    public void showLine() {
        out.print(System.lineSeparator());
    }

    private void showNoTasksMsg() {
        out.println("There are currently no tasks in your list.");
    }

    private void showSingleTask(Task task) {
        out.printf("  %s\n", task);
    }

    private void showTaskTotal(TaskList tasks) {
        int total = tasks.size();
        out.printf("Now you have %d task%s in the list.\n", total, total > 1 ? "s" : "");
    }
}
