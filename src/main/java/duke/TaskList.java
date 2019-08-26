package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a new list of tasks.
 * An <code>TaskList</code> object corresponds to a new list that stores <code>Task</code> objects.
 */
public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Constructor for <code>TaskList</code>.
     * This constructor will be used if there are already tasks stored in the hard disk.
     * @param tasks List of tasks that were loaded from the hard disk.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Second constructor for <code>TaskList</code>.
     * This constructor will be used if there are no tasks stored in the hard disk.
     */
    public TaskList() {
    }

    /**
     * Returns the list of tasks that are stored in this object.
     * @return List of <code>Task</code> objects.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns the number of tasks.
     * @return Size of the list.
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * Adds a new <code>Task</code> object to the list of tasks.
     * @param task Instance of <code>Task</code>.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a <code>Task</code> object from the list of tasks.
     * @param index List index of the task to be removed.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the <code>Task</code> object at that list index.
     * @param index List index of the task.
     * @return Task that is stored at the input index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
}
