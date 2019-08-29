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
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Getter for size variable.
     *
     * @return Size of task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns if TaskList is empty.
     *
     * @return true if no task in list, true if there are tasks in list.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns index of a task if task is in list.
     * @param t Task
     * @return Index of task
     * @throws DukeException If task not in taskList.
     */
    public int indexOf(Task t) throws DukeException {
        if (this.getTasks().contains(t)) {
            return tasks.indexOf(t);
        } else {
            throw new DukeException("Task not in taskList.");
        }
    }

    /**
     *
     * @param index
     * @return
     * @throws DukeException
     */
    public Task get(int index) throws DukeException {
        if (isValidIndex(index)) {
            return tasks.get(index - 1);
        } else {
            throw new DukeException("\u2754 OOPS!!! There is no task at index " + index + ".");
        }
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void remove(Task t) throws DukeException {
        if (tasks.contains(t)) {
            tasks.remove(t);
        } else {
            throw new DukeException("\u2754 OOPS!!! There is no such task in taskList.");
        }
    }

    public Task remove(int index) throws DukeException {
        Task task;
        if (isValidIndex(index)) {
            task = tasks.remove(index - 1);
        } else {
            throw new DukeException("\u2754 OOPS!!! There is no such task in taskList.");
        }
        return task;
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }
}
