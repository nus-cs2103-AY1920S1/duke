package duke.task;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the list of tasks in the application.
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
        assert this.tasks != null;
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
        assert !this.isEmpty();
    }

    /**
     * Prints the list of tasks to the UI.
     */
    public void printList() {
        for (Task task : tasks) {
            int i = getId(task);
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
    public int getId(Task task) {
        int id = this.tasks.indexOf(task) + 1;
        // getId should only ever be called on tasks that are present in the list
        assert id > 1;
        return this.tasks.indexOf(task) + 1;
    }

    /**
     * Returns the task with the given ID in this TaskList.
     * @param taskId ID of the task.
     * @return Task with the given ID.
     * @throws IndexOutOfBoundsException Exception thrown when the given ID is not present in
     * the TaskList.
     */
    public Task getTask(int taskId) throws IndexOutOfBoundsException {
        return tasks.get(taskId - 1);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Deletes the given task from the TaskList.
     * @param taskToDelete Task to be deleted from the TaskList.
     */
    public void deleteTask(Task taskToDelete) {
        assert tasks.contains(taskToDelete);
        tasks.remove(taskToDelete);
    }

    /**
     * Returns a TaskList containing tasks that match the given keyword.
     * @param keyword Keyword to be used to find tasks.
     * @return TaskList containing tasks that match the given keyword.
     */
    public TaskList findTasks(String keyword) {
        // is it possible to implement without creating temporary TaskList object?
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
