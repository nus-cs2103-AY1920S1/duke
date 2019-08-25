package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String prefix = "☹ OOPS!!!";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Handles the actual printing of error messages to the system output.
     * All printing of error messages should be routed through this method.
     * @param errorMessage Error message to be printed
     */
    private void showError(String errorMessage) {
        System.out.println(prefix + " " + errorMessage);
    }

    public void showError(DukeException e) {
        showError(e.getMessage());
    }

    /**
     * Handles the actual printing of messages to the system output.
     * All printing of messages should be routed through this method.
     * @param message Message to be printed
     */
    private void showMessage(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println();
    }

    public void showLoadingError() {
        showError("Could not load your tasks.");
    }

    public void showSavingError() {
        showError("Could not save your tasks.");
    }

    public void showWelcome() {
        showMessage("Hello! I'm Duke");
        showMessage("What can I do for you?");
    }

    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public void showTask(Task task) {
        showMessage("  " + task);
    }

    public void showTaskDone(Task task) {
        showMessage("Nice! I've marked this task as done:");
        showTask(task);
    }

    public void showTaskAdded(Task task) {
        showMessage("Got it. I've added this task:");
        showTask(task);
    }

    public void showTaskDeleted(Task task) {
        showMessage("Noted. I've removed this task:");
        showTask(task);
    }

    public void showNumTasks(int num) {
        showMessage("Now you have " + num + " task" + (num == 1 ? "" : "s") + " in the list.");
    }

    /**
     * Shows all the tasks in a list of tasks.
     * @param tasks A list of tasks
     */
    public void showAllTasks(List<Task> tasks) {
        if (tasks.size() == 0) {
            showMessage("There are no tasks in your list.");
        } else {
            showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                showMessage((i + 1) + "." + task);
            }
        }
    }

    public void showFilteredTasks(List<Task> tasks, String filter) {
        showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(filter)) {
                showMessage((i + 1) + "." + task);
            }
        }
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
