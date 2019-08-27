package duke.task;


import java.util.ArrayList;

/**
 * Abstraction of a in-memory list of tasks of the user.
 */
public class TaskList {
    /** The array list of task objects used to back the TaskList. */
    private ArrayList<Task> tasks;
    /** The current number of tasks in the list. */
    private int size;

    /**
     * Constructor of the task list.
     * Initializes a new empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.size = 0;
    }

    /**
     * Retrieves the underlying array list of tasks.
     *
     * @return The array list of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Retrieves the number of tasks.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the task associated with the index.
     * Essentially a wrapper around array list's get method.
     *
     * @param index The integer index.
     * @return The associated task.
     */
    public Task getTaskByIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes the task associated with the index.
     * Also adjusts the task list size.
     * Essentially a wrapper around array list's remove method.
     *
     * @param index The integer index.
     * @return The associated deleted task.
     */
    public Task deleteTaskByIndex(int index) {
        size--;
        return tasks.remove(index);
    }

    /**
     * Adds the specified task to the task list.
     * Also adjusts the task list size.
     * Essentially a wrapper around array list's add method.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        size++;
        tasks.add(task);
    }
}
