package seedu.duke.storage;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.trackables.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstraction of a List of Tasks.
 */
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
            throw new InvalidArgumentException("No task with id " + (index + 1) + " exists.");
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
            throw new InvalidArgumentException("No task with id " + (index + 1) + " exists.");
        }
        return tasks.remove(index);
    }

    /**
     * Finds all Tasks that contain the pattern specified.
     * @param pattern The pattern used for the search.
     * @return Returns a new TaskList with the matching tasks.
     */
    public TaskList findByDescription(String pattern) {
        TaskList searchResult;
        searchResult = new TaskList(tasks.stream()
            .filter(task -> task.getDescription().contains(pattern))
            .collect(Collectors.toList()));
        return searchResult;
    }

    /**
     * Converts the TaskList into a String, each line representing a task. Typically used to display
     * all Tasks in the list to the user.
     * @return Returns the list as a single String.
     */
    public String getListAsString() {
        StringBuilder response = new StringBuilder();

        response.append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(i + 1)
                .append(".")
                .append(tasks.get(i).toString());

            if (i < tasks.size()) {
                response.append("\n");
            }
        }
        return response.toString();
    }

    public int size() {
        return this.tasks.size();
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < this.tasks.size();
    }
}
