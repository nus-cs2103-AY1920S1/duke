package util;

import tasks.Task;

import java.util.ArrayList;

/**
 * Contains the task list and operations on the list.
 *
 *  @author atharvjoshi
 *  @version CS2103 AY19/20 Sem 1 iP Week 3
 */
public class TaskList {
    /** Internal task list kept as an array list of task objects */
    private ArrayList<Task> tasks;

    /**
     * Creates new task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Checks if list is empty.
     *
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns size of the list as an integer.
     *
     * @return int representing size of the list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds given task to the list.
     *
     * @param task the task to be added
     */
    public boolean add(Task task) {
        return this.tasks.add(task);
    }

    /**
     * Returns task from the list at specified index.
     *
     * @param index the index of the task to be returned
     * @return the specified task
     * @throws IndexOutOfBoundsException if index specified > list size
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index);
    }

    /**
     * Removes task at specified index from the task list.
     *
     * @param index the index of the task to be removed
     * @throws IndexOutOfBoundsException if index specified > list size
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        this.tasks.remove(index);
    }
}
