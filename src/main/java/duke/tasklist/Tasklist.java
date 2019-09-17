package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class Tasklist {
    public ArrayList<Task> tasks;

    public Tasklist() {
        tasks = new ArrayList<>(100); // Specification said numTasks < 100.
    }

    public Tasklist(ArrayList<Task> list) {
        tasks = list;
    }

    /**
     * Adds task to the list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the size of the list.
     * @return the size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index.
     * @param index The index provided by the user.
     * @return the task at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the given index.
     * @param index The index provided by the user.
     * @return the task removed at the given index.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }
}
