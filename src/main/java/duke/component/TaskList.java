package duke.component;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 * Encapsulates and represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        tasks = new LinkedList<>();
    }

    /**
     * Adds a task into the tasks list.
     *
     * @param t the task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a task form the tasks list.
     *
     * @param i index of the task to be removed.
     * @return The task that has been removed from the list.
     * @throws DukeException if the index given is < 0 or > size - 1, where size is the
     *     number of tasks in the list.
     */
    public Task removeTask(int i) throws DukeException {
        if (!indexIsValid(i)) {
            throw new DukeException("Index must be between 1 and the number of task added!");
        }

        return tasks.remove(i);
    }

    /**
     * Returns the task at the given index in the list.
     *
     * @param index index of the task (starts from 0).
     * @return the task at the given index in the list.
     * @throws DukeException if the index is invalid (see documentation on the removeTask method).
     */
    public Task getTask(int index) throws DukeException {
        if (!indexIsValid(index)) {
            throw new DukeException("Index must be between 1 and the number of task added!");
        }

        return tasks.get(index);
    }

    /**
     * Replaces the task located at specified position in the list with the new specified task.
     *
     * @param index index of the task to replace.
     * @param task the new task to be stored at the specified position.
     * @throws DukeException if the index is out of range (index < 0 || index >= size()).
     */
    public void setTask(int index, Task task) throws DukeException {
        if (!indexIsValid(index)) {
            throw new DukeException("Index must be between 1 and the number of task added!");
        }

        tasks.set(index, task);
    }

    /**
     * Returns the size(number of tasks) of the list.
     *
     * @return the size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Applies the given consumer on every tasks in the list sequentially.
     *
     * @param c consumer specifying action(s) to be applied on a task in the list.
     */
    public void forEach(Consumer<Task> c) {
        tasks.forEach(c);
    }

    /**
     * Returns the list iterator of the list.
     *
     * @return the list iterator of the list.
     */
    public ListIterator<Task> listIterator() {
        return tasks.listIterator();
    }

    /**
     * Checks if a given index to access a task in the list is valid.
     *
     * @param index the given index.
     * @return true if the index is valid and false otherwise.
     */
    private boolean indexIsValid(int index) {
        return index >= 0 && index < tasks.size();
    }
}
