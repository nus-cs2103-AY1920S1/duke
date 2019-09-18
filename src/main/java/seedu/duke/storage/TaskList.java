package seedu.duke.storage;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.trackables.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> newTaskList) {
        this.tasks = newTaskList;
    }

    // CRUD Tasks

    /**
     * Adds the task {@code newTask} to {@code tasks}.
     * @param newTask The task to add.
     */
    public void add(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Retrieves the Task at the given index.
     * @param index The index of the task to retrieve.
     * @return The Task at the given index.
     * @throws InvalidArgumentException Thrown when the index is out of range.
     */
    public Task get(int index) throws InvalidArgumentException {
        if (!isValidIndex(index)) {
            throw new InvalidArgumentException("No task with id " + index + " exists.");
        }
        return this.tasks.get(index);
    }

    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Substitutes the task at the given index with a new task.
     * @param index Index of task to replace.
     * @param newTask Task to use as replacement.
     * @throws InvalidArgumentException Thrown when the index is out of range.
     */
    public void update(int index, Task newTask) throws InvalidArgumentException {
        if (!isValidIndex(index)) {
            throw new InvalidArgumentException("No task with id " + index + " exists.");
        }
        this.tasks.set(index, newTask);
    }

    /**
     * Removes the Task from the list at the specified index.
     * @param index The index of the task to delete.
     * @return The task that is removed.
     * @throws InvalidArgumentException Thrown when the index is out of range.
     */
    public Task remove(int index) throws InvalidArgumentException {
        if (!isValidIndex(index)) {
            throw new InvalidArgumentException("No task with id " + index + " exists.");
        }
        return tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < this.tasks.size();
    }
}
