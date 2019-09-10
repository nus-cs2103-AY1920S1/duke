package duke.core;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Represents a list of <code>Task</code> that can perform operations such as 
 * add and delete on the tasks.
 */
public class TaskList {
    /**
     * An internal <code>ArrayList</code> structure to store and manipulate
     * the tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a new <code>TaskList</code> with a default empty list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor that constructs a new <code>TaskList</code> from
     * a given <code>ArrayList</code> of <code>Task</code>.
     *
     * @param tasks An existing <code>ArrayList</code> of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the <code>ArrayList</code> of tasks associated with this
     * <code>TaskList</code>.
     *
     * @return The <code>ArrayList</code> of tasks associated with this
     * <code>TaskList</code>.
     */
    ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns the <code>Task</code> in the list with the given index.
     *
     * @param i The index of the <code>Task</code>.
     * @return The <code>Task</code> in the list with this specific index.
     */
    public Task getTask(int i) {
        assert i >= 0 : "TaskList index cannot be negative";
        return tasks.get(i);
    }

    /**
     * Adds a <code>Task</code> to the list.
     *
     * @param t The <code>Task</code> to be added to the list.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes the <code>Task</code> with the given index from the list.
     *
     * @param i The index of the <code>Task</code> to be deleted.
     */
    public void removeTask(int i) {
        assert i >= 0 : "TaskList index cannot be negative";
        tasks.remove(i);
    }

    /**
     * Returns the number of tasks stored in the list.
     *
     * @return An integer representing the number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }
}




