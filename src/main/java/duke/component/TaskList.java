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
     * number of tasks in the list.
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
     * @param i index of the task (starts from 0).
     * @return the task at the given index in the list.
     * @throws DukeException if the index is invalid (see documentation on the removeTask method).
     */
    public Task getTask(int i) throws DukeException {
        if (!indexIsValid(i)) {
            throw new DukeException("Index must be between 1 and the number of task added!");
        }

        return tasks.get(i);
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
