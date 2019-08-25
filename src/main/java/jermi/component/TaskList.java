package jermi.component;

import jermi.task.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class to represent a list of tasks.
 */
public class TaskList {
    /** List of tasks */
    private List<Task> list;

    /**
     * Public constructor for class.
     * Uses an {@link ArrayList} to store the tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns the list containing the tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getList() {
        return this.list;
    }

    /**
     * Adds task to the list.
     *
     * @param task Task.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Returns the task of the specified index in the list.
     *
     * @param index Index (starting from 1) of task.
     * @return Task of the specified index.
     */
    public Task getTask(int index) {
        return this.list.get(index - 1);
    }

    /**
     * Removes the task of the specified index in the list.
     *
     * @param index Index (starting from 1) of task.
     */
    public void remove(int index) {
        this.list.remove(index - 1);
    }

    public List<String> find(String keyword) {
        return this.list
                .stream()
                .map(Task::toString)
                .filter(task -> task.contains(keyword))
                .collect(Collectors.toList());
    }
}
