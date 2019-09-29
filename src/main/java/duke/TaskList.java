package duke;

import java.util.ArrayList;
import task.Task;

/**
 * TaskList class that abstract the ArrayList Task object.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for tasklist.
     *
     * @param list ArrayList representation for the task list.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Overriden method for tasklist if there is no initial seeding of data.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds given task into list.
     *
     * @param task Given task object to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Gets number of tasks.
     *
     * @return Current size of list.
     */
    public int getTaskSize() {
        return list.size();
    }

    /**
     * Gets the ArrayList representation.
     *
     * @return ArrayList representation.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Gets the task by its number.
     *
     * @param index the task number.
     * @return The task at the specific number.
     */
    public Task getTaskByIndex(int index) {
        return this.list.get(index - 1);
    }

    /**
     * Marks the specified task to be done.
     *
     * @param index Task number of the task to be marked as done.
     */
    public void markDone(int index) {
        Task task = list.get(index - 1);
        task.markAsDone();
    }

    /**
     * Clears all existing item in the list.
     */
    public void clearAll() {
        list.clear();
    }

    /**
     * Removes task by its given task number.
     *
     * @param index Task number of the task to be removed.
     * @return Task object that has been removed.
     */
    public Task removeTaskByIndex(int index) {
        return list.remove(index - 1);
    }
}


