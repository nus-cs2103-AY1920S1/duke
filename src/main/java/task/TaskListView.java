package task;

import task.tasks.Task;
import util.DukeMessage;
import util.DukeOutput;

import java.util.List;

/***
 * <p>
 * View in charge of printing all task related information and output.
 * </p>
 */
class TaskListView {
    /***
     * <p>
     * Prints tasks.
     * </p>
     * @param tasks list of tasks to be printed.
     */
    public void displayAllTasks(List<Task> tasks) {
        DukeMessage taskList = new DukeMessage("Here are the tasks in your list:");

        appendTasks(taskList, tasks);

        DukeOutput.printMessage(taskList);
    }

    /***
     * <p>
     * Prints matching tasks.
     * </p>
     * @param tasks list of matching tasks to be printed.
     */
    public void displayMatchingTasks(List<Task> tasks) {
        DukeMessage taskList = new DukeMessage("Here are the matching tasks in your list:");

        appendTasks(taskList, tasks);

        DukeOutput.printMessage(taskList);
    }

    private DukeMessage appendTasks(DukeMessage message, List<Task> tasks) {
        message.newLine();

        if (!tasks.isEmpty()) {
            message.append("1.")
                    .append(formatTaskMessage(tasks.get(0)));
        }

        for (int i = 1; i < tasks.size(); i++) {
            message.newLine()
                    .append(i + 1)
                    .append(".")
                    .append(formatTaskMessage(tasks.get(i)));
        }

        return message;
    }

    private DukeMessage formatTaskMessage(Task task) {
        return new DukeMessage(task.getTaskMessage());
    }

    /***
     * <p>
     * Prints feedback and task's descriptor message when task is added.
     * </p>
     * @param task task to be added.
     * @param tasksLength current task list length.
     */
    public void displayNewTask(Task task, int tasksLength) {
        DukeMessage newTaskMessage = new DukeMessage("Got it. I've added this task:")
                .newLine()
                .indent()
                .append(task.getTaskMessage())
                .newLine()
                .append(String.format("Now you have %d tasks in the list", tasksLength));
        DukeOutput.printMessage(newTaskMessage);
    }

    /***
     * <p>
     * Prints feedback and task's descriptor message when task is marked as done.
     * </p>
     * @param task task to be marked as done
     */
    public void displayTaskDone(Task task) {
        DukeMessage taskDoneMessage = new DukeMessage("Nice! I've marked this task as done:")
                .newLine()
                .indent()
                .append(task.getTaskMessage());
        DukeOutput.printMessage(taskDoneMessage);
    }

    /***
     * <p>
     * Prints feedback and task's descriptor message when task is deleted.
     * </p>
     * @param task task to be deleted.
     * @param tasksLength length of task list after deletion.
     */
    public void displayTaskDeleted(Task task, int tasksLength) {
        DukeMessage taskDeletedMessage = new DukeMessage("Noted. I've removed this task:")
                .newLine()
                .indent()
                .append(task.getTaskMessage())
                .newLine()
                .append(String.format("Now you have %d tasks in the list", tasksLength));
        DukeOutput.printMessage(taskDeletedMessage);
    }
}
