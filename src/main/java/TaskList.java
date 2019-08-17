import java.util.ArrayList;

//@@author Parcly-Taxel
/**
 * A class that holds a list of tasks that may be added to, removed or
 * marked as done. This list is indexed starting from 1.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    
    /**
     * Initialises an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }
    
    /**
     * Returns the number of tasks in this list.
     * @returns The number of tasks.
     */
    public int size() {
        return tasks.size();
    }
    
    /**
     * Gets the task at position i in the list.
     * @param i The index of the task to be retrieved, starting from 1.
     * @returns The task at position i.
     * @throws IndexOutOfBoundsException If position i is not in the list.
     */
    public Task get(int i) throws IndexOutOfBoundsException {
        return tasks.get(i);
    }
    
    /**
     * Adds a task to the end of the list.
     * @param t The task to be added.
     * @returns The task that was just added.
     */
    public Task addTask(Task t) {
        tasks.add(t);
        return t;
    }
    
    /**
     * Marks the task at one-based index i as done.
     * @param i The index of the task to be marked done, starting from 1.
     * @returns The task at position i, which was just marked done.
     * @throws IndexOutOfBoundsException If position i is not in the list.
     */
    public Task markDone(int i) throws IndexOutOfBoundsException {
        tasks.get(i - 1).markDone();
        return tasks.get(i - 1);
    }
    
    /**
     * Removes the task at one-based index i.
     * @param i The index of the task to be removed, starting from 1.
     * @returns The task that used to be at position i.
     * @throws IndexOutOfBoundsException If position i is not in the list.
     */
    public Task removeTask(int i) throws IndexOutOfBoundsException {
        return tasks.remove(i - 1);
    }
}
