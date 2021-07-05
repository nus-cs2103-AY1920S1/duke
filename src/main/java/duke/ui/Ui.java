package duke.ui;

import duke.DukeException;
import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public abstract class Ui {
    protected static final String prefix = "☹ OOPS!!!";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Handles the actual printing of error messages to the system output.
     * All printing of error messages should be routed through this method.
     * @param errorMessage Error message to be printed
     */
    protected abstract void showError(String errorMessage);

    public void showError(DukeException e) {
        showError(e.getMessage());
    }

    /**
     * Handles the actual printing of messages to the system output.
     * All printing of messages should be routed through this method.
     * @param message Message to be printed
     */
    protected abstract void showMessage(String message);

    public abstract void showLine();

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        showError("Could not load your tasks.");
    }

    public void showSavingError() {
        showError("Could not save your tasks.");
    }

    public void showWelcome() {
        showMessage("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public void showTaskDone(Task task) {
        showMessage("Nice! I've marked this task as done:\n  " + task);
    }

    public void showTaskAdded(Task task, int numTasks) {
        showMessage("Got it. I've added this task:\n  " + task + "\n" + numTasksMessage(numTasks));
    }

    public void showTaskDeleted(Task task, int numTasks) {
        showMessage("Noted. I've removed this task:\n  " + task + "\n" + numTasksMessage(numTasks));
    }

    public void showTaskEdited(Task task) {
        showMessage("Got it. I've edited this task:\n  " + task);
    }

    public String numTasksMessage(int num) {
        return "Now you have " + num + " task" + (num == 1 ? "" : "s") + " in the list.";
    }

    /**
     * Shows all the tasks in a list of tasks.
     * @param tasks A list of tasks
     */
    public void showAllTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("There are no tasks in your list.");
        } else {
            sb.append("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                sb.append("\n");
                sb.append((i + 1) + "." + task);
            }
        }
        showMessage(sb.toString());
    }

    /**
     * Shows tasks in a list of tasks that match a given filter string.
     * @param tasks A list of tasks
     * @param filter Filter string
     */
    public void showFilteredTasks(List<Task> tasks, String filter) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(filter)) {
                sb.append("\n");
                sb.append((i + 1) + "." + task);
            }
        }
        showMessage(sb.toString());
    }
}
