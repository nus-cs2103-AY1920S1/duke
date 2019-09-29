package duke.task;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * List of tasks in the application.
 */
public class TaskList implements Serializable {

    /** ArrayList containing tasks in the TaskList. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList containing the tasks in the list provided.
     * @param tasks ArrayList containing the tasks to be included in the new TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a new, empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns ArrayList containing the tasks in the TaskList.
     * @return ArrayList containing the tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns true if the TaskList does not contain any tasks.
     * @return True if the TaskList does not contain any tasks.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds a new task to the list.
     * @param task New task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Prints the list of tasks to the UI.
     */
    // todo: move to Ui class
    public void printList() {
        for (Task task : tasks) {
            int i = getId(task, this);
            String s = task.toString();
            System.out.println(i + ". " + s);
        }
    }

    /**
     * Returns the ID of the given task, in the given TaskList.
     * Helper function for printList() method.
     * @param task Task whose ID is required.
     * @return ID of the given task.
     */
    private int getId(Task task, TaskList taskList) {
        return taskList.tasks.indexOf(task) + 1;
    }

    /**
     * Returns the task with the given ID in the main TaskList.
     * @param taskId ID of the task.
     * @return Task with the given ID.
     * @throws IndexOutOfBoundsException Exception thrown when the given ID is not present in
     * the TaskList.
     */
    public Task getTask(int taskId) throws IndexOutOfBoundsException {
        return tasks.get(taskId - 1);
    }

    /**
     * Deletes the given task from the TaskList.
     * @param taskId ID of task to be deleted from the TaskList.
     */
    public void deleteTask(int taskId) {
        Task taskToDelete = getTask(taskId);
        String taskDescription = taskToDelete.toString();
        tasks.remove(taskToDelete);
        System.out.println("Noted. I've removed this task: " + taskDescription);
        System.out.println("Now you have " + tasks.size() + " items in this list.");
    }

    /**
     * Returns a TaskList containing tasks that match the given keyword.
     * @param keyword Keyword to be used to find tasks.
     * @return TaskList containing tasks that match the given keyword.
     */
    public TaskList findTasks(String keyword) {
        // todo: is it possible to implement without creating temporary TaskList object?
        // so that the numbering of the tasks based on the original list can be preserved
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
