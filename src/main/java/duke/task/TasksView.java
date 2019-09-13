package duke.task;

import error.ui.UiException;
import ui.UiController;
import util.OutputBuilder;

import java.util.List;

/***
 * <p>
 * View in charge of printing all task related information and output.
 * </p>
 */
public class TasksView {
    /***
     * <p>
     * Prints tasks.
     * </p>
     * @param tasks list of tasks to be printed.
     */
    public void displayAllTasks(List<Task> tasks, UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Here are the tasks in your list:")
                .newLine()
                .appendTasks(tasks);

        String output = builder.build();
        ui.displayOutput(output);
    }

    /***
     * <p>
     * Prints matching tasks.
     * </p>
     * @param tasks list of matching tasks to be printed.
     */
    public void displaySearchResults(List<Task> tasks, UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Here are the matching tasks in your list:")
                .appendTasks(tasks);

        String output = builder.build();
        ui.displayOutput(output);
    }


    /***
     * <p>
     * Prints feedback and task's descriptor message when task is added.
     * </p>
     * @param task task to be added.
     * @param tasksLength current task list length.
     */
    public void displayNewTask(Task task, int tasksLength, UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Got it. I've added this task:")
                .newLine()
                .indent()
                .append(task.getDescription())
                .newLine()
                .append(String.format("Now you have %d tasks in the list", tasksLength));

        String output = builder.build();
        ui.displayOutput(output);
    }

    /***
     * <p>
     * Prints feedback and task's descriptor message when task is marked as done.
     * </p>
     * @param task task to be marked as done
     */
    public void displayTaskDone(Task task, UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Nice! I've marked this task as done:")
                .newLine()
                .indent()
                .append(task.getDescription());

        String output = builder.build();
        ui.displayOutput(output);
    }

    /***
     * <p>
     * Prints feedback and task's descriptor message when task is deleted.
     * </p>
     * @param task task to be deleted.
     * @param tasksLength length of task list after deletion.
     */
    public void displayTaskDeleted(Task task, int tasksLength, UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Noted. I've removed this task:")
                .newLine()
                .indent()
                .append(task.getDescription())
                .newLine()
                .append(String.format("Now you have %d tasks in the list", tasksLength));

        String output = builder.build();
        ui.displayOutput(output);
    }

    public void displayTaskUndone(Task task, UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Noted. I've unmarked this task as undone:")
                .newLine()
                .indent()
                .append(task.getDescription());

        String output = builder.build();
        ui.displayOutput(output);
    }
}
