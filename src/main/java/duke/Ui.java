package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String PREFIX = "    ";
    private static final String DIVIDER = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private static final String ADD_MESSAGE = "Got it. I've added this task:";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private Scanner in;
    private PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    private Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    public void showError(DukeException e) {
        showToUser(e.getMessage());
    }

    public void showWelcomeMessage() {
        showToUser(DIVIDER, WELCOME_MESSAGE, DIVIDER);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showAddMessage(Task task, long taskCount) {
        showToUser(ADD_MESSAGE,
                task.toString(),
                (taskCount == 1
                    ?"Now you have 1 task in the list."
                    : String.format("Now you have %d tasks in the list.", taskCount)));
    }

    public void showDoneMessage(Task task) {
        showToUser(DONE_MESSAGE,
                task.toString());
    }

    public void showDeleteMessage(Task task, long taskCount) {
        showToUser(DELETE_MESSAGE,
                task.toString(),
                (taskCount == 1
                    ? "Now you have 1 task in the list."
                    : String.format("Now you have %d tasks in the list.", taskCount)));
    }

    public void showTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task != null) { // account for 0-indexing
                showToUser(String.format("%d. %s", i, task));
            }
        }
    }

    public void showExitMessage() {
        showToUser(EXIT_MESSAGE);
    }

    private void showToUser(String... messages) {
        for (String line: messages) {
            out.println(PREFIX + line);
        }
    }
}
