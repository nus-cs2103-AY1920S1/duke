package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter for list of tasks.
     *
     * @return List of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Getter for size variable.
     *
     * @return Size of task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns if TaskList is empty.
     *
     * @return true if no task in list, true if there are tasks in list
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns index of a task if task is in list.
     * @param t Task
     * @return Index of task
     * @throws DukeException If task not in taskList
     */
    public int indexOf(Task t) throws DukeException {
        if (this.getTasks().contains(t)) {
            return tasks.indexOf(t);
        } else {
            throw new DukeException("Task not in taskList.");
        }
    }

    /**
     * Get Task from list at index.
     *
     * @param index Index of task in list
     * @return Task at index
     * @throws DukeException If index is out of range of list
     */
    public Task get(int index) throws DukeException {
        if (isValidIndex(index)) {
            return tasks.get(index - 1);
        } else {
            throw new DukeException("OOPS!!! There is no task at index " + index + ".");
        }
    }

    /**
     * Add task to list.
     *
     * @param t Task to be added to list
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Remove task from task list.
     *
     * @param t Task to be removed
     * @throws DukeException If task is not in task list
     */
    public void remove(Task t) throws DukeException {
        if (tasks.contains(t)) {
            tasks.remove(t);
        } else {
            throw new DukeException("OOPS!!! There is no such task in taskList.");
        }
    }

    /**
     * Remove task at index from list.
     *
     * @param index Index at which to remove task
     * @return Removed task
     * @throws DukeException If index is out of range of list
     */
    public Task remove(int index) throws DukeException {
        Task task;
        if (isValidIndex(index)) {
            task = tasks.remove(index - 1);
        } else {
            throw new DukeException("OOPS!!! There is no such task in taskList.");
        }
        return task;
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }
}
