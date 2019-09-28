package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents the list of tasks currently tracked by Duke.
 */
public class TaskList implements Iterable<Task> {
    /** List of tasks. */
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the ArrayList of task's iterator.
     *
     * @return ArrayList of task's iterator.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Returns the number of tasks currently tracked in the TaskList.
     *
     * @return number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds the Task to be tracked into TaskList.
     *
     * @param task Task to be added into TaskList.
     */
    public void add(Task task) throws DukeException {
        assert task != null : "Task should not be null.";

        if (tasks.contains(task)) {
            throw new DukeException("Sorry! This task is already tracked by Duke!");
        }

        tasks.add(task);
    }

    /**
     * Returns the Task in the TaskList associated with the ID.
     * The ID of the task is the index of the task in the ArrayList plus 1.
     *
     * @param taskId ID of task.
     * @return Task associated with the ID.
     * @throws DukeException If the ID does not belong to any task or if the ID is not a number.
     */
    public Task get(String taskId) throws DukeException {
        try {
            return tasks.get(Integer.parseInt(taskId) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task ID input!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found!");
        }
    }

    /**
     * Removes the Task in the TaskList associated with the ID.
     * The ID of the task is the index of the task in the ArrayList plus 1.
     *
     * @param taskId ID of task to be removed.
     * @return Returns the removed task with the associated the ID.
     * @throws DukeException If the ID does not belong to any task or if the ID is not a number.
     */
    public Task remove(String taskId) throws DukeException {
        try {
            return tasks.remove(Integer.parseInt(taskId) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task ID input!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found!");
        }
    }
}
