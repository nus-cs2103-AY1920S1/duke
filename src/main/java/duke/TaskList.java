package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list containing the elements of the specified ArrayList of Tasks,
     * in the order they are returned by the list's iterator.
     *
     * @param tasks the ArrayList of Tasks whose tasks are to be placed into this task list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the task at the specified position in this task list.
     *
     * @param index the 1-based index of the task to return
     * @return the task at the specified position in this task list
     * @throws DukeException if the index is out of range (index < 0 || index >= size())
     */
    public Task get(int index) throws DukeException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    String.format("Task No.%d is not present in your list. "
                            + "Please enter a valid task ID.", index));
        }
    }

    /**
     * Appends the specified task to the end of this task list.
     *
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified position in this task list.
     * Shifts any subsequent tasks to the left (subtracts one from their indices).
     *
     * @param taskId the 1-based index of the task to be removed
     * @return the task that was removed from the list
     * @throws DukeException if the index is out of range (index < 0 || index >= size())
     */
    public Task remove(int index) throws DukeException {
        Task task = get(index);
        tasks.remove(task);
        return task;
    }

    /**
     * Returns the number of tasks in this task list.
     *
     * @return the number of tasks in this task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns true if this task list contains no tasks.
     *
     * @return true if this task list contains no tasks
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Filters this task list and returns a new task list
     * which only contains tasks with keyword appearing in its description.
     *
     * @param keyWord the keyword to be used for filtering this task list
     * @return a TaskList with all the tasks containing the keyWord
     */
    public TaskList filter(String keyWord) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyWord)) {
                newList.add(task);
            }
        }
        return new TaskList(newList);
    }
}