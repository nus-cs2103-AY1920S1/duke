package task;

import java.util.ArrayList;

/**
 * A class that holds a list of tasks that may be added to, removed or
 * marked as done. This list is indexed starting from 1.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    
    /**
     * Initialises an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }
    
    /**
     * Returns the number of tasks in this list.
     */
    public int size() {
        return tasks.size();
    }
    
    /**
     * Adds an undone task to the end of the list.
     */
    public void add(Task t) {
        tasks.add(t);
    }
    
    /**
     * Gets the task at one-indexed position i in the list.
     *
     * <code>get()</code>, <code>markDone()</code> and <code>removeTask()</code>
     * all take an index i starting from 1, raising an exception if that index is invalid.
     *
     * @param i The index of the task to be retrieved, starting from 1.
     * @return The task at position i.
     * @throws IndexOutOfBoundsException If position i is not in the list.
     */
    public Task get(int i) throws IndexOutOfBoundsException {
        return tasks.get(i - 1);
    }
    
    /**
     * Marks the task at index i as done.
     *
     * @see #get(int)
     */
    public void markDone(int i) throws IndexOutOfBoundsException {
        tasks.get(i - 1).markDone();
    }
    
    /**
     * Removes the task at index i and returns it.
     *
     * @see #get(int)
     */
    public Task delete(int i) throws IndexOutOfBoundsException {
        return tasks.remove(i - 1);
    }
}
