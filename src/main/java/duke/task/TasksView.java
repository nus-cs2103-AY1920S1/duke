package duke.task;

import duke.command.entities.TaskSorts;
import error.ui.UiException;
import ui.UiController;
import util.OutputBuilder;

import java.util.List;

/**
 * Class in charge of printing all task related information and output.
 */
public class TasksView {
    /**
     * Prints tasks.
     * @param tasks list of tasks to be printed.
     * @param ui ui to print output
     */
    public void displayAllTasks(List<Task> tasks, UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Here are the tasks in your list:")
                .newLine()
                .appendTasks(tasks);

        String output = builder.build();
        ui.displayOutput(output);
    }

    /**
     * Prints matching tasks.
     * @param tasks list of matching tasks to be printed.
     * @param ui ui to print output
     */
    public void displaySearchResults(List<Task> tasks, UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Here are the matching tasks in your list:")
                .appendTasks(tasks);

        String output = builder.build();
        ui.displayOutput(output);
    }


    /**
     * Prints feedback and task's descriptor message when task is added.
     * @param task task to be added.
     * @param tasksLength current task list length.
     * @param ui ui to print output
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

    /**
     * Prints feedback and task's descriptor message when task is marked as done.
     * @param task task to be marked as done
     * @param ui ui to print output
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

    /**
     * Prints feedback and task's descriptor message when task is deleted.
     * @param task task to be deleted.
     * @param tasksLength length of task list after deletion.
     * @param ui ui to print
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

    /**
     * Prints feedback and task's descriptor message when task is marked as undone.
     * @param task task to be marked as undone
     * @param ui to print output
     */
    public void displayTaskUndone(Task task, UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Noted. I've unmarked this task as undone:")
                .newLine()
                .indent()
                .append(task.getDescription());

        String output = builder.build();
        ui.displayOutput(output);
    }

    public void displayTasksSorted(TaskSorts sortingMethod, UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Noted. I have sorted your tasks according to " + sortingMethod.keyword);

        String output = builder.build();
        ui.displayOutput(output);
    }

    public void displayNewTasksSet(int tasksLength, UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Noted. I have set your tasks to the new list:")
                .newLine()
                .append(String.format("Now you have %d tasks in the list", tasksLength));

        String output = builder.build();
        ui.displayOutput(output);
    }

    public void displayTaskReplaced(Task replaced, Task task,  UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Noted. I have replaced the task:")
                .newLine()
                .indent()
                .append(replaced.getDescription())
                .newLine()
                .append("Your new task is:")
                .newLine()
                .indent()
                .append(task.getDescription());

        String output = builder.build();
        ui.displayOutput(output);
    }

    public void displayAllTasksDeleted(UiController ui) throws UiException {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Noted. I have deleted all your tasks.")
                .newLine()
                .append("Now you have 0 tasks in the list.");

        String output = builder.build();
        ui.displayOutput(output);
    }
}
