package ui;

import task.Task;
import task.TaskList;

import java.util.stream.IntStream;

public abstract class Ui {
    protected StringBuilder messageBuilder = new StringBuilder();

    public Ui() {
    }

    /**
     * Show message to user.
     */
    public abstract void show();

    /**
     * Add a line to messageBuilder.
     *
     * @param line String containing a line to add to messageBuilder.
     */
    public abstract void addLineToMessage(String line);

    /**
     * Print out message for adding a task.
     */
    public void showAddTask(Task task, int taskCount) {
        addLineToMessage("Got it mate. I've added this task:");
        addLineToMessage(task.toString());
        addLineToMessage("Now you have " + taskCount + " tasks in the list mate.");
        show();
    }

    /**
     * Print out message for setting a task to done.
     *
     * @param task Task to set as done.
     */
    public void showDoneTask(Task task) {
        addLineToMessage("Nice one mate! I've marked this task as done:");
        addLineToMessage("  " + task);
        show();
    }

    /**
     * Print out message for deleting a task.
     *
     * @param task      Task to delete.
     * @param taskCount Current number of tasks in list.
     */
    public void showDeleteTask(Task task, int taskCount) {
        addLineToMessage("Noted mate! I've removed this task:");
        addLineToMessage("  " + task);
        addLineToMessage("Now you have " + taskCount + " tasks in the list mate.");
        show();
    }

    /**
     * Print out list of tasks.
     *
     * @param tasks TaskList to print out.
     */
    public void listTasks(TaskList tasks) {
        if (tasks.size() > 0) {
            addLineToMessage("Here are the tasks in your list:");
            IntStream.range(0, tasks.size())
                .forEach(i -> {
                    addLineToMessage((i + 1) + ". " + tasks.get(i));
                });
        } else {
            addLineToMessage("Can't see any tasks in the list, start adding tasks mate!");
        }

        show();
    }

    /**
     * Print out list of tasks matching search string.
     *
     * @param matchingTasks TaskList to print out.
     */
    public void showFoundTasks(TaskList matchingTasks) {
        if (matchingTasks.size() > 0) {
            addLineToMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                addLineToMessage((i + 1) + ". " + matchingTasks.get(i));
            }
        } else {
            addLineToMessage("Mate, I found no matching tasks. Try something else.");
        }

        show();
    }

    /**
     * Print out loading error.
     */
    public void showLoadingError() {
        showError("Oops! Unable to load tasks from hard disk, starting with an empty list.");
    }

    /**
     * Print out welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n\t"
                + "|  _ \\ _   _| | _____ \n\t"
                + "| | | | | | | |/ / _ \\\n\t"
                + "| |_| | |_| |   <  __/\n\t"
                + "|____/ \\__,_|_|\\_\\___|\n\t";
        String greeting = logo + "G'day mate! I'm Duke.\n\tWhatcha need help with?";
        addLineToMessage(greeting);
        show();
    }

    /**
     * Print out exit message.
     */
    public void showBye() {
        String bye = "Bye mate. Catch you later!";
        addLineToMessage(bye);
        show();
    }

    /**
     * Print out generic error.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        addLineToMessage(message);
        show();
    }

}
