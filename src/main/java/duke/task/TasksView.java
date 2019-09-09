package duke.task;

import duke.task.tasks.Task;
import error.UiException;
import ui.UiController;
import util.OutputBuilder;
import util.DukeOutput;

import java.util.List;

/***
 * <p>
 * View in charge of printing all duke.task related information and output.
 * </p>
 */
public class TasksView {
    private UiController ui;

    public TasksView(UiController ui) {
            this.ui = ui;
    }

    /***
     * <p>
     * Prints tasks.
     * </p>
     * @param tasks list of tasks to be printed.
     */
    public void displayAllTasks(List<Task> tasks) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Here are the tasks in your list:")
                .newLine()
                .appendTasks(tasks);

        String output = builder.build();
        displayMessage(output);
    }

    /***
     * <p>
     * Prints matching tasks.
     * </p>
     * @param tasks list of matching tasks to be printed.
     */
    public void displaySearchResults(List<Task> tasks) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Here are the matching tasks in your list:")
                .appendTasks(tasks);

        String output = builder.build();
        displayMessage(output);
    }


    /***
     * <p>
     * Prints feedback and duke.task's descriptor message when duke.task is added.
     * </p>
     * @param task duke.task to be added.
     * @param tasksLength current duke.task list length.
     */
    public void displayNewTask(Task task, int tasksLength) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Got it. I've added this duke.task:")
                .newLine()
                .indent()
                .append(task.getDisplayMessage())
                .newLine()
                .append(String.format("Now you have %d tasks in the list", tasksLength));

        String output = builder.build();
        displayMessage(output);
    }

    /***
     * <p>
     * Prints feedback and duke.task's descriptor message when duke.task is marked as done.
     * </p>
     * @param task duke.task to be marked as done
     */
    public void displayTaskDone(Task task) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Nice! I've marked this duke.task as done:")
                .newLine()
                .indent()
                .append(task.getDisplayMessage());

        String output = builder.build();
        displayMessage(output);
    }

    /***
     * <p>
     * Prints feedback and duke.task's descriptor message when duke.task is deleted.
     * </p>
     * @param task duke.task to be deleted.
     * @param tasksLength length of duke.task list after deletion.
     */
    public void displayTaskDeleted(Task task, int tasksLength) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Noted. I've removed this duke.task:")
                .newLine()
                .indent()
                .append(task.getDisplayMessage())
                .newLine()
                .append(String.format("Now you have %d tasks in the list", tasksLength));

        String output = builder.build();
        displayMessage(output);
    }

    public void displayError(Exception e) {
        displayMessage(e.getMessage());
    }

    private void displayMessage(String message) {
        try {
            ui.displayOutput(message);
        } catch (UiException e) {
            System.out.println("FATAL: " + e.getMessage());
            System.exit(1);
        }
    }
}
